import ast
import threading
import numpy as np
import cv2
from types import ModuleType
from typing import Dict, Any, Optional
import platform
import logging
from skimage import filters, feature, transform, morphology, exposure, color

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

class SecurityException(Exception):
    pass

class TimeoutException(Exception):
    pass

def set_memory_limit(limit_mb=500):
    """设置内存限制（Windows下跳过）"""
    if platform.system() != 'Windows':
        import resource
        soft, hard = resource.getrlimit(resource.RLIMIT_AS)
        resource.setrlimit(resource.RLIMIT_AS, (limit_mb * 1024 * 1024, hard))
    else:
        logger.warning("Windows does not support memory limits. Skipping.")

def execute_restricted(code: str, local_vars: Dict[str, Any], timeout: Optional[int] = None):
    """
    在受限环境中执行Python代码（使用timeout-decorator实现超时）
    """
    # 1. 静态代码分析
    try:
        parsed = ast.parse(code)
    except SyntaxError as e:
        raise SecurityException(f"语法错误: {e}")

    # 检查危险操作
    for node in ast.walk(parsed):
        if isinstance(node, ast.Import) or isinstance(node, ast.ImportFrom):
            raise SecurityException("不允许直接使用import语句")
        if isinstance(node, ast.Call) and isinstance(node.func, ast.Name):
            if node.func.id in ['eval', 'exec', 'open', 'exit', 'quit', 'os.system', 'subprocess']:
                raise SecurityException(f"不允许调用危险函数: {node.func.id}")

    # 2. 准备安全环境
    restricted_globals = {
        '__builtins__': {
            'range': range, 'len': len, 'print': print,
            'int': int, 'float': float, 'str': str,
            'list': list, 'dict': dict, 'min': min,
            'max': max, 'sum': sum, 'abs': abs,
            'round': round, 'zip': zip, 'enumerate': enumerate,
            'isinstance': isinstance
        },
        'np': _create_restricted_numpy(),
        'cv2': _create_restricted_opencv(),
        'math': _create_restricted_math(),
        'skimage': _create_restricted_skimage(),
        'os': None, 'sys': None, '__file__': None,
        '__name__': '__restricted__'
    }

    # 3. 使用线程实现超时控制
    class ExecThread(threading.Thread):
        def __init__(self):
            super().__init__()
            self.result = None
            self.error = None

        def run(self):
            try:
                exec(code, restricted_globals, local_vars)
                self.result = local_vars.get('output_image')
            except Exception as e:
                self.error = e

    thread = ExecThread()
    thread.daemon = True  # 设置为守护线程
    thread.start()
    thread.join(timeout=timeout)

    if thread.error:
        raise SecurityException(str(thread.error))
    if thread.is_alive():
        raise TimeoutException(f"执行超时 (>{timeout}秒)")
    if thread.result is None:
        raise SecurityException("Script did not set 'output_image' variable")

    return thread.result


# 以下辅助函数
def _create_restricted_numpy() -> ModuleType:
    """创建受限制的NumPy环境"""
    allowed_numpy = {
        # 数组创建
        'array': np.array,
        'zeros': np.zeros,
        'ones': np.ones,
        'empty': np.empty,
        'full': np.full,
        'zeros_like': np.zeros_like,  # 新增
        'ones_like': np.ones_like,  # 新增
        'empty_like': np.empty_like,  # 新增
        'full_like': np.full_like,  # 新增
        'arange': np.arange,
        'linspace': np.linspace,
        'logspace': np.logspace,
        'meshgrid': np.meshgrid,

        # 数学运算
        'abs': np.abs,
        'add': np.add,
        'subtract': np.subtract,
        'multiply': np.multiply,
        'divide': np.divide,
        'power': np.power,
        'sqrt': np.sqrt,
        'exp': np.exp,
        'log': np.log,
        'log10': np.log10,
        'sin': np.sin,
        'cos': np.cos,
        'tan': np.tan,
        'arcsin': np.arcsin,
        'arccos': np.arccos,
        'arctan': np.arctan,
        'sinh': np.sinh,
        'cosh': np.cosh,
        'tanh': np.tanh,

        # 统计运算
        'mean': np.mean,
        'median': np.median,
        'std': np.std,
        'var': np.var,
        'amin': np.amin,
        'amax': np.amax,
        'percentile': np.percentile,
        'sum': np.sum,
        'prod': np.prod,
        'cumsum': np.cumsum,
        'cumprod': np.cumprod,

        # 数组操作
        'reshape': np.reshape,
        'transpose': np.transpose,
        'concatenate': np.concatenate,
        'stack': np.stack,
        'hstack': np.hstack,
        'vstack': np.vstack,
        'split': np.split,
        'tile': np.tile,
        'repeat': np.repeat,
        'where': np.where,
        'clip': np.clip,
        'sort': np.sort,
        'argsort': np.argsort,

        # 数据类型
        'int8': np.int8,
        'int16': np.int16,
        'int32': np.int32,
        'int64': np.int64,
        'uint8': np.uint8,
        'uint16': np.uint16,
        'uint32': np.uint32,
        'uint64': np.uint64,
        'float16': np.float16,
        'float32': np.float32,
        'float64': np.float64,
        'bool': np.bool_,

        # 常量和属性
        'pi': np.pi,
        'e': np.e,
        'inf': np.inf,
        'nan': np.nan,

        # 方法访问
        'astype': lambda x: x.astype,
        'shape': lambda x: x.shape,
        'dtype': lambda x: x.dtype,
        'size': lambda x: x.size
    }

    restricted_np = ModuleType('numpy')
    for name, obj in allowed_numpy.items():
        setattr(restricted_np, name, obj)
    return restricted_np

def _create_restricted_opencv() -> ModuleType:
    """创建受限制的OpenCV环境"""
    allowed_cv2 = {
        'cvtColor': cv2.cvtColor,
        'threshold': cv2.threshold,
        'GaussianBlur': cv2.GaussianBlur,
        'medianBlur': cv2.medianBlur,
        'Canny': cv2.Canny,
        'Sobel': cv2.Sobel,
        'Laplacian': cv2.Laplacian,
        'resize': cv2.resize,
        'getRotationMatrix2D': cv2.getRotationMatrix2D,
        'warpAffine': cv2.warpAffine,
        'morphologyEx': cv2.morphologyEx,  # 添加缺失的形态学操作
        'erode': cv2.erode,               # 添加腐蚀操作
        'dilate': cv2.dilate,             # 添加膨胀操作
        'merge':cv2.merge,
        'convertScaleAbs':cv2.convertScaleAbs,
        # 常量
        'COLOR_BGR2GRAY': cv2.COLOR_BGR2GRAY,
        'COLOR_BGR2HSV': cv2.COLOR_BGR2HSV,
        'THRESH_BINARY': cv2.THRESH_BINARY,
        'THRESH_OTSU': cv2.THRESH_OTSU,
        'INTER_LINEAR': cv2.INTER_LINEAR,
        'BORDER_REFLECT': cv2.BORDER_REFLECT,
        # 形态学操作常量
        'MORPH_ERODE': cv2.MORPH_ERODE,
        'MORPH_DILATE': cv2.MORPH_DILATE,
        'MORPH_OPEN': cv2.MORPH_OPEN,
        'MORPH_CLOSE': cv2.MORPH_CLOSE,
        'MORPH_GRADIENT': cv2.MORPH_GRADIENT,
        'MORPH_TOPHAT': cv2.MORPH_TOPHAT,
        'MORPH_BLACKHAT': cv2.MORPH_BLACKHAT,
        'MORPH_RECT': cv2.MORPH_RECT,
        'MORPH_CROSS': cv2.MORPH_CROSS,
        'MORPH_ELLIPSE': cv2.MORPH_ELLIPSE
    }
    restricted_cv2 = ModuleType('cv2')
    for name, obj in allowed_cv2.items():
        setattr(restricted_cv2, name, obj)
    return restricted_cv2

def _create_restricted_math() -> ModuleType:
    """创建受限制的math模块"""
    import math
    allowed_math = {
        # 基本数学函数
        'sqrt': math.sqrt,
        'pow': math.pow,
        'exp': math.exp,
        'log': math.log,
        'log10': math.log10,
        'log2': math.log2,
        'sin': math.sin,
        'cos': math.cos,
        'tan': math.tan,
        'asin': math.asin,
        'acos': math.acos,
        'atan': math.atan,
        'atan2': math.atan2,
        'sinh': math.sinh,
        'cosh': math.cosh,
        'tanh': math.tanh,
        'asinh': math.asinh,
        'acosh': math.acosh,
        'atanh': math.atanh,

        # 角度转换
        'degrees': math.degrees,
        'radians': math.radians,

        # 舍入函数
        'ceil': math.ceil,
        'floor': math.floor,
        'trunc': math.trunc,
        'isclose': math.isclose,

        # 特殊函数
        'erf': math.erf,
        'erfc': math.erfc,
        'gamma': math.gamma,
        'lgamma': math.lgamma,

        # 常量
        'pi': math.pi,
        'e': math.e,
        'inf': math.inf,
        'nan': math.nan,
        'tau': math.tau
    }
    restricted_math = ModuleType('math')
    for name, obj in allowed_math.items():
        setattr(restricted_math, name, obj)
    return restricted_math


def _create_restricted_skimage() -> ModuleType:
    allowed_skimage = {
        # 滤波器
        'filters': {
            'gaussian': filters.gaussian,
            'sobel': filters.sobel,
            'prewitt': filters.prewitt,
            'roberts': filters.roberts,
            'median': filters.median,
            'threshold_otsu': filters.threshold_otsu,
            'threshold_local': filters.threshold_local
        },
        # 特征检测
        'feature': {
            'canny': feature.canny,
            'hog': feature.hog
        },
        # 几何变换
        'transform': {
            'rotate': transform.rotate,
            'resize': transform.resize,
            'warp': transform.warp
        },
        # 形态学操作
        'morphology': {
            'erosion': morphology.erosion,
            'dilation': morphology.dilation,
            'opening': morphology.opening,
            'closing': morphology.closing
        },
        # 图像增强
        'exposure': {
            'equalize_hist': exposure.equalize_hist,
            'adjust_gamma': exposure.adjust_gamma,
            'adjust_log': exposure.adjust_log
        },
        # 颜色空间转换
        'color': {
            'rgb2gray': color.rgb2gray,
            'rgb2hsv': color.rgb2hsv,
            'hsv2rgb': color.hsv2rgb
        }
    }

    restricted_skimage = ModuleType('skimage')
    for module_name, module_funcs in allowed_skimage.items():
        module_obj = ModuleType(module_name)
        for func_name, func in module_funcs.items():
            setattr(module_obj, func_name, func)
        setattr(restricted_skimage, module_name, module_obj)

    return restricted_skimage