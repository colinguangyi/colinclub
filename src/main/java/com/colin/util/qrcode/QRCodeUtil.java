package com.colin.util.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具
 * @author zhaolz
 * @create 2017-09-18
 */
public class QRCodeUtil {
    //图片宽，高
    private int width,height;
    //图片格式
    private String format;
    //数据编码
    private String encode;
    //二维码内边距（最外层白边宽度）
    private int margin;

    public QRCodeUtil(int width, int height){
        this.width = width;
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    /**
     * 生成二维码
     * @param content 数据内容
     * @param imgPath 生成图片路径
     */
    public void createQRCode(String content, String imgPath) throws Exception {
        Map<EncodeHintType,Object> hints = new HashMap<EncodeHintType, Object>();

        //设置编码
        hints.put(EncodeHintType.CHARACTER_SET, this.encode);
        //设置纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置内边距
        hints.put(EncodeHintType.MARGIN, this.margin);
        //生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,
                this.width, this.height, hints);

        //将二维码写入流中
        //MatrixToImageWriter.writeToStream(bitMatrix, format, outputstream);

        //将二维码写入指定路径
        Path file = new File(imgPath).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, format, file);
    }

    /**
     * 解析二维码
     * @param imgPath 图片路径
     * @return 返回结果
     */
    public Result parseQRCode(String imgPath) throws Exception {
        //设置解析参数
        Map<DecodeHintType,Object> hints = new HashMap<DecodeHintType, Object>();
        //设置编码
        hints.put(DecodeHintType.CHARACTER_SET, this.encode);
        //获取图片流
        File file = new File(imgPath);
        MultiFormatReader formatReader = new MultiFormatReader();
        BufferedImage bufferedImage = ImageIO.read(file);
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        //解析
        Result result = formatReader.decode(binaryBitmap, hints);
        return result;
    }

}
