package com.tiger.widget;

public class MaterialsConsumption {


    /**
     * oid : 8
     * rawMaterial : 水泥
     * rawMaterialCode : GM0001
     * totalSignTon : 0
     * totalConsumeTon : 30.69
     */

    private int oid;
    private String rawMaterial;
    private String rawMaterialCode;
    private double totalSignTon;
    private double totalConsumeTon;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(String rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public String getRawMaterialCode() {
        return rawMaterialCode;
    }

    public void setRawMaterialCode(String rawMaterialCode) {
        this.rawMaterialCode = rawMaterialCode;
    }

    public double getTotalSignTon() {
        return totalSignTon;
    }

    public void setTotalSignTon(double totalSignTon) {
        this.totalSignTon = totalSignTon;
    }

    public double getTotalConsumeTon() {
        return totalConsumeTon;
    }

    public void setTotalConsumeTon(double totalConsumeTon) {
        this.totalConsumeTon = totalConsumeTon;
    }
}
