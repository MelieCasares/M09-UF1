package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class TextXifrat {
    public byte[] bytes;


    public TextXifrat(byte[] bytes) {

    }
    
    public byte[] getBytes() {
        return this.bytes;
    }


    @Override
    public String toString() {
        return new String(this.bytes, StandardCharsets.UTF_8);
    }
}
