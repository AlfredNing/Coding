package build;

/**
 * @author Alfred.Ning
 * @since 2023年02月08日 08:05:00
 */
public class Car {

    // 尺寸
    private String size;

    // 方向盘
    private String steeringWheel;

    // 底座
    private String pedestal;

    // 轮胎
    private String wheel;

    // 排量
    private String displacement;

    // 最大速度
    private String maxSpeed;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(String steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public String getPedestal() {
        return pedestal;
    }

    public void setPedestal(String pedestal) {
        this.pedestal = pedestal;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [size=" + size + ", steeringWheel=" + steeringWheel + ", pedestal=" + pedestal + ", wheel=" + wheel
                + ", displacement=" + displacement + ", maxSpeed=" + maxSpeed + "]";
    }

    public Car(CarBuilder builder) {
        if ("紧凑型车".equals(builder.getType())) {
            this.size = "大小--紧凑型车";
        } else if ("中型车".equals(builder.getType())) {
            this.size = "大小--中型车";
        } else {
            this.size = "大小--其他";
        }

        if ("很舒适".equals(builder.getComfort())) {
            this.steeringWheel = "方向盘--很舒适";
            this.pedestal = "底座--很舒适";
        } else if ("一般舒适".equals(builder.getComfort())) {
            this.steeringWheel = "方向盘--一般舒适";
            this.pedestal = "底座--一般舒适";
        } else {
            this.steeringWheel = "方向盘--其他";
            this.pedestal = "底座--其他";
        }

        if ("动力强劲".equals(builder.getPower())) {
            this.displacement = "排量--动力强劲";
            this.maxSpeed = "最大速度--动力强劲";
            this.steeringWheel = "轮胎--动力强劲";
        } else if ("动力一般".equals(builder.getPower())) {
            this.displacement = "排量--动力一般";
            this.maxSpeed = "最大速度--动力一般";
            this.steeringWheel = "轮胎--动力一般";
        } else {
            this.displacement = "排量--其他";
            this.maxSpeed = "最大速度--其他";
            this.steeringWheel = "轮胎--其他";
        }
    }
}
