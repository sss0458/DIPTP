from fastapi import FastAPI, UploadFile, File, Form, HTTPException
import cv2
import numpy as np
import restricted_env
import time
from typing import Optional
from fastapi.responses import JSONResponse
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware
import base64

app = FastAPI(title="Python Algorithm Executor")

# 允许跨域
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# 配置常量
MAX_SCRIPT_LENGTH = 10000  # 10KB
MAX_EXECUTION_TIME = 10  # 10秒
MAX_FILE_SIZE = 10 * 1024 * 1024  # 10MB


class ExecutionResult(BaseModel):
    success: bool
    output_image: Optional[str] = None  # Base64编码的图像
    execution_time: Optional[float] = None
    error: Optional[str] = None
    logs: Optional[str] = None


@app.post("/execute", response_model=ExecutionResult)
async def execute_algorithm(
        file: UploadFile = File(...),
        script: str = Form(...),
        timeout: Optional[int] = Form(MAX_EXECUTION_TIME)
):
    # 1. 基础验证
    if len(script) > MAX_SCRIPT_LENGTH:
        return JSONResponse(
            status_code=400,
            content={"success": False, "error": f"Script too large (max {MAX_SCRIPT_LENGTH} chars)"}
        )

    if timeout > 30:  # 最大允许30秒
        return JSONResponse(
            status_code=400,
            content={"success": False, "error": "Timeout too long (max 30 seconds)"}
        )

    # 2. 读取图像
    try:
        contents = await file.read()
        if len(contents) > MAX_FILE_SIZE:
            return JSONResponse(
                status_code=400,
                content={"success": False, "error": f"File too large (max {MAX_FILE_SIZE / 1024 / 1024}MB)"}
            )

        nparr = np.frombuffer(contents, np.uint8)
        input_img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
        if input_img is None:
            return JSONResponse(
                status_code=400,
                content={"success": False, "error": "Invalid image file"}
            )
    except Exception as e:
        return JSONResponse(
            status_code=400,
            content={"success": False, "error": f"Image processing error: {str(e)}"}
        )

    # 3. 准备执行环境
    local_vars = {
        'input_image': input_img,
        'output_image': None,
        'width': input_img.shape[1],
        'height': input_img.shape[0],
        'channels': input_img.shape[2],
        '_logs': []  # 用于收集日志
    }

    # 替换print函数以收集日志
    def restricted_print(*args, **kwargs):
        local_vars['_logs'].append(" ".join(str(arg) for arg in args))
        print(*args, **kwargs)  # 仍然输出到控制台

    # 4. 执行脚本 (带超时控制)
    start_time = time.time()
    try:
        restricted_env.execute_restricted(script, local_vars, timeout)
    except Exception as e:
        return JSONResponse(
            status_code=400,
            content={
                "success": False,
                "error": str(e),
                "execution_time": time.time() - start_time,
                "logs": "\n".join(local_vars.get('_logs', []))
            }
        )

    # 检查执行时间
    execution_time = time.time() - start_time

    # 5. 获取结果
    output_img = local_vars.get('output_image')
    if output_img is None:
        return JSONResponse(
            status_code=400,
            content={
                "success": False,
                "error": "Script did not set 'output_image' variable",
                "execution_time": execution_time,
                "logs": "\n".join(local_vars.get('_logs', []))
            }
        )

    if not isinstance(output_img, np.ndarray):
        return JSONResponse(
            status_code=400,
            content={
                "success": False,
                "error": "Output must be a numpy array",
                "execution_time": execution_time,
                "logs": "\n".join(local_vars.get('_logs', []))
            }
        )

    # 6. 返回处理后的图像
    try:
        _, encoded_img = cv2.imencode('.png', output_img)
        img_base64 = base64.b64encode(encoded_img).decode('utf-8')
        return {
            "success": True,
            "output_image": f"{img_base64}",  # 前端可直接用于<img>标签
            "execution_time": execution_time,
            "logs": "\n".join(local_vars.get('_logs', []))
        }
    except Exception as e:
        return JSONResponse(
            status_code=500,
            content={
                "success": False,
                "error": f"Result encoding failed: {str(e)}",
                "execution_time": execution_time,
                "logs": "\n".join(local_vars.get('_logs', []))
            }
        )