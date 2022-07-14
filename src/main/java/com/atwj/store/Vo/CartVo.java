package com.atwj.store.Vo;

/**
 * @author 吴先森
 * @description: 在多表查询时，所查询到的数据不适合pojo层的实体类，需要创建vo层实体类
 * @create 2022-05-24 17:31
 */
public class CartVo {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVo)) return false;

        CartVo cartVo = (CartVo) o;

        if (getCid() != null ? !getCid().equals(cartVo.getCid()) : cartVo.getCid() != null) return false;
        if (getUid() != null ? !getUid().equals(cartVo.getUid()) : cartVo.getUid() != null) return false;
        if (getPid() != null ? !getPid().equals(cartVo.getPid()) : cartVo.getPid() != null) return false;
        if (getPrice() != null ? !getPrice().equals(cartVo.getPrice()) : cartVo.getPrice() != null) return false;
        if (getNum() != null ? !getNum().equals(cartVo.getNum()) : cartVo.getNum() != null) return false;
        if (getTitle() != null ? !getTitle().equals(cartVo.getTitle()) : cartVo.getTitle() != null) return false;
        if (getRealPrice() != null ? !getRealPrice().equals(cartVo.getRealPrice()) : cartVo.getRealPrice() != null)
            return false;
        return getImage() != null ? getImage().equals(cartVo.getImage()) : cartVo.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = getCid() != null ? getCid().hashCode() : 0;
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getPid() != null ? getPid().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getRealPrice() != null ? getRealPrice().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", realPrice=" + realPrice +
                ", image='" + image + '\'' +
                '}';
    }
}
