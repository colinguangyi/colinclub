package com.colin.util.qrcode;

import com.google.zxing.Result;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaolz
 * @create 2017-09-18
 */
public class Test {
    public static void main(String[] args) {
        String content = "https://www.mumu18.com";
        String imgPath = "C:\\Users\\zlz\\Desktop\\img.png";
        QRCodeUtil qrCodeUtil = new QRCodeUtil(300, 300);
        qrCodeUtil.setEncode("utf-8");
        qrCodeUtil.setFormat("png");
        qrCodeUtil.setMargin(2);
        try {
            qrCodeUtil.createQRCode(content, imgPath);
            TimeUnit.SECONDS.sleep(3);
            Result result = qrCodeUtil.parseQRCode(imgPath);
            System.out.println("result: " + result.toString());
            System.out.println("格式： " + result.getBarcodeFormat());
            System.out.println("文本： " + result.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
