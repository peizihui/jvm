package com.bjut.QklTest;

import java.io.Serializable;
import java.util.Date;

public class Block implements Serializable {
    public String hash; //存放数字签名
    public String preHash; //前面块的签名
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String preHash) {
        this.data = data;
        this.preHash = preHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(preHash + Long.toString(timeStamp) + data);
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!" + hash);
    }

}
