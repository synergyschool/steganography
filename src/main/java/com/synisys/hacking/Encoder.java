package com.synisys.hacking;

/**
 * Created by Hayk Martirosyan on 2/16/16.
 *
 *
 * Encodes data using LSB(least significant bit)  steganography algorithm
 */
public class Encoder {
    private static final int NUMBER_OF_BITS_IN_BYTE = 8;

    private final byte[] data;
    private int offset = 0;

    public Encoder(byte[] data){
        this.data = data;
    }


    public void encodeInt(final int intToEncode) {
        //write from high byte to low
        encodeByte((byte) (intToEncode>>24 & 0xFF));
        encodeByte((byte) (intToEncode>>16 & 0xFF));
        encodeByte((byte) (intToEncode>>8 & 0xFF));
        encodeByte((byte) (intToEncode & 0xFF));
    }



    public void encodeByte(final byte byteToEncode) {
        //write from high bit to low
        int mask = 0b10000000;
        for(int i=0; i<NUMBER_OF_BITS_IN_BYTE; i++){
            int currentBit = (byteToEncode & mask)==0?0:1;

            this.data[this.offset + i] = (byte)((this.data[this.offset + i]&0xFE) | currentBit);
            mask = mask>>1;
        }

        this.offset += NUMBER_OF_BITS_IN_BYTE;
    }



}
