import org.junit.Test;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.io.File;

public class OpenCVTest {

    static {
        // 或指定绝对路径（如果动态库不在系统路径中）：
        System.load("C:\\Users\\dell\\Desktop\\RuoYi-Vue\\lib\\opencv_java3416.dll");
    }

    @Test
    public void testOpenCV() {
        // 1. 检查 OpenCV 版本
        System.out.println("OpenCV Version: " + Core.VERSION);

        // 2. 读取测试图片（示例路径，需替换为实际图片路径）
        String imagePath = "src/test/test_photo.jpg";
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            throw new RuntimeException("无法加载图片，请检查路径: " + imagePath);
        }
        System.out.println("图片加载成功，尺寸: " + image.width() + "x" + image.height());

        // 3. 执行边缘检测（Canny算法）
        Mat edges = new Mat();
        Imgproc.Canny(image, edges, 100, 200);
        System.out.println("边缘检测完成");

        // 4. 保存结果到临时文件
        String outputPath = "target/test-output.jpg";
        Imgcodecs.imwrite(outputPath, edges);
        System.out.println("结果已保存至: " + new File(outputPath).getAbsolutePath());
    }
}