package com.synisys.hacking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.synisys.hacking.Decoder;
import com.synisys.hacking.Encoder;



/**
 * Created by hayk.martirosyan on 2/17/16.
 *
 *
 */
public class EncoderTest {
    private final byte []data = new byte[1000_000];

    @Before
    public void setup(){
        for(int i = 0; i< data.length; i++) {
            data[i] = (byte) i;
        }
    }


    @Test
    public void testEncoderByteMethod(){
        byte[]dataClone = data.clone();

        Encoder encoder = new Encoder(dataClone);
        encoder.encodeByte((byte) 0);
        encoder.encodeByte((byte) 1);
        encoder.encodeByte((byte) 128);
        encoder.encodeByte((byte) 255);
        Decoder decoder = new Decoder(dataClone);

        Assert.assertEquals(0, decoder.decodeByte());
        Assert.assertEquals(1, decoder.decodeByte());
        Assert.assertEquals(128, decoder.decodeByte());
        Assert.assertEquals(255, decoder.decodeByte());
    }

    @Test
    public void testEncoderIntMethod(){
        byte[]dataClone = data.clone();

        Encoder encoder = new Encoder(dataClone);
        encoder.encodeInt(0);
        encoder.encodeInt(1);
        encoder.encodeInt(255);
        encoder.encodeInt(256);

        Decoder decoder = new Decoder(dataClone);

        Assert.assertEquals(0, decoder.decodeInt());
        Assert.assertEquals(1, decoder.decodeInt());
        Assert.assertEquals(255, decoder.decodeInt());
        Assert.assertEquals(256, decoder.decodeInt());
    }

    @Test
    public void testEncoder(){


        byte[]dataClone = data.clone();

        Encoder encoder = new Encoder(dataClone);
        for(int i = 0; i<256; i++){
            encoder.encodeByte((byte) i);
        }
        for(int i = 0; i<256; i++) {
            encoder.encodeInt(i);
            encoder.encodeInt(0xFF+i);
            encoder.encodeInt(0xFFFF+i);
            encoder.encodeInt(0xFFFFFF+i);
        }

        for(int i=0;i<dataClone.length; i++){
            Assert.assertEquals(data[i]&0xFE, dataClone[i]&0xFE);
        }
        Decoder decoder = new Decoder(dataClone);
        for(int i = 0; i<256; i++){
            Assert.assertEquals(i, decoder.decodeByte());
        }
        for(int i = 0; i<256; i++){
            Assert.assertEquals(i, decoder.decodeInt());
            Assert.assertEquals(0xFF + i, decoder.decodeInt());
            Assert.assertEquals(0xFFFF + i, decoder.decodeInt());
            Assert.assertEquals(0xFFFFFF + i, decoder.decodeInt());
        }

    }
}
