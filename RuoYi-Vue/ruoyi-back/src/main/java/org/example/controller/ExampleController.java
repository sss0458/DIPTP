package org.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.example.domain.vo.ExampleVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.example.domain.Example;
import org.example.service.IExampleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 案例库Controller
 * 
 * @author ruoyi
 * @date 2025-06-30
 */
@RestController
@RequestMapping("/example")
public class ExampleController extends BaseController
{
    @Autowired
    private IExampleService exampleService;

    /**
     * 查询案例库列表
     */
    @PreAuthorize("@ss.hasPermi('example:example:list')")
    @GetMapping("/list")
    public TableDataInfo list(Example example)
    {
        startPage();
        List<Example> list = exampleService.selectExampleList(example);
        return getDataTable(list);
    }


    /**
     * 根据案例ID获取所有案例数据
     * @param exampleId 案例ID
     * @return 包含对应案例数据的视图对象
     */
    @PreAuthorize("@ss.hasPermi('example:example:query')")
    @GetMapping("/fullexample/{exampleId}")
    public AjaxResult getFullExampleDataByExampleId(@PathVariable Long exampleId) {

       ExampleVo exampleVo =  exampleService.selectExampleVoById(exampleId);
        if (exampleVo == null) {
            return error("案例不存在");
        }
        if (exampleVo.getCasePath()!= null && !exampleVo.getCasePath().isEmpty()) {
            try {
                byte[] fileContent = Files.readAllBytes(Paths.get(exampleVo.getCasePath()));
                String base64Image = Base64.getEncoder().encodeToString(fileContent);
                exampleVo.setImageBase64(base64Image);
            } catch (IOException e) {
                // 如果读取失败，imageBase64保持为null
            }
        }
        return success(exampleVo);
    }

    /**
     * 获取所有案例数据
     *
     * @return 包含所有案例数据的视图对象列表
     */
    @PreAuthorize("@ss.hasPermi('example:example:list')")
    @GetMapping("/full/all")
    public AjaxResult getAllFullExampleData() {
        List<ExampleVo>  exampleVos = exampleService.selectExampleVoList();
        for(ExampleVo exampleVo:exampleVos){
            if (exampleVo.getCasePath()!= null && !exampleVo.getCasePath().isEmpty()) {
                try {
                    byte[] fileContent = Files.readAllBytes(Paths.get(exampleVo.getCasePath()));
                    String base64Image = Base64.getEncoder().encodeToString(fileContent);
                    exampleVo.setImageBase64(base64Image);
                } catch (IOException e) {
                    // 如果读取失败，imageBase64保持为null
                }
            }
        }
        return success(exampleVos);
    }


    /**
     * 导出案例库列表
     */
    @PreAuthorize("@ss.hasPermi('example:example:export')")
    @Log(title = "案例库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Example example)
    {
        List<Example> list = exampleService.selectExampleList(example);
        ExcelUtil<Example> util = new ExcelUtil<Example>(Example.class);
        util.exportExcel(response, list, "案例库数据");
    }


    @Log(title = "案例库管理", businessType = BusinessType.IMPORT)
   // @PreAuthorize("@ss.hasPermi('system:example:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Example> util = new ExcelUtil<Example>(Example.class);
        List<Example> exampleList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = exampleService.importExample(exampleList, updateSupport, operName);
        return success(message);
    }


    /**
     * 获取案例库详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:example:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(exampleService.selectExampleById(id));
    }

    /**
     * 新增案例库
     */
    @PreAuthorize("@ss.hasPermi('example:example:add')")
    @Log(title = "案例库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Example example)
    {
        return toAjax(exampleService.insertExample(example));
    }

    /**
     * 修改案例库
     */
    @PreAuthorize("@ss.hasPermi('example:example:edit')")
    @Log(title = "案例库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Example example)
    {
        return toAjax(exampleService.updateExample(example));
    }

    /**
     * 删除案例库
     */
    @PreAuthorize("@ss.hasPermi('example:example:remove')")
    @Log(title = "案例库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(exampleService.deleteExampleByIds(ids));
    }

    /**
     * 获取案例统计信息
     */
    //@PreAuthorize("@ss.hasPermi('system:example:statistics')")
    @GetMapping("/statistics")
    public AjaxResult getStatistics() {
        return AjaxResult.success(exampleService.getExampleStatistics());
    }
}
