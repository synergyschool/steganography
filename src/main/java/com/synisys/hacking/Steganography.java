package com.synisys.hacking;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

/**
 * Created by Hayk Martirosyan  on 2/16/16.
 *
 *
 * Encodes data using LSB(least significant bit)  steganography algorithm
 */
public class Steganography {



    public void encode(InputStream sourceImageStream, InputStream secretFileStream,
                      OutputStream targetOutputStream) throws IOException {
        BufferedImage sourceImage = ImageIO.read(sourceImageStream);
        byte []sourceBytes = ((DataBufferByte)sourceImage.getRaster().getDataBuffer()).getData();
        byte []secretBytes = IOUtils.toByteArray(secretFileStream);

        Encoder encoder = new Encoder(sourceBytes);
        encoder.encodeInt(secretBytes.length);
        for(int i = 0; i < secretBytes.length; i++) {
            encoder.encodeByte(secretBytes[i]);
        }

        ImageIO.write(sourceImage, "PNG", targetOutputStream);
    }

    public void decode(InputStream sourceImageStream, OutputStream secretFileOutputStream) throws IOException {
        /*
        **ff*redI**ge so**ce*mage = I***eIO.r**d(s**rce*****S***am);
        **te []s**rce**tes = ((D***Bu***r**te)s**rc**mage.g**R**te*().g***at***ffer()).g***ata();
        De****r ****der= ne* D****er(s**rc**ytes);
        i*t f**e*en**h = d**od**.d*c**e***();
        **r(i*t i = 0; i < fi**L**gt*; i++) {
            *e**et**le**t**t*t**am.**it*(d**od**.**co*e*y*e());
        }
        */
    }


    public static void main(String[] args) throws IOException {
        final String PATH  = "/Users/hayk.martirosyan/Downloads/";
        new Steganography().encode(new FileInputStream(PATH + "Container.png"), new FileInputStream(PATH + "secret-file.png"), new FileOutputStream(PATH + "encripted.png"));
        new Steganography().decode(new FileInputStream(PATH + "encripted.png"), new FileOutputStream(PATH + "secret-file.png"));
    }

}
