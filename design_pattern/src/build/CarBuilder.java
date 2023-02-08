package build;

/**
 * @author Alfred.Ning
 * @since 2023年02月08日 08:06:00
 */
public class CarBuilder {

    // 车型
    private String type;

    // 动力
    private String power;

    // 舒适性
    private String comfort;

    public Car build() {
        assert type != null;
        assert power != null;
        assert power != null;
        return new Car(this);
    }

    public String getType() {
        return type;
    }

    public CarBuilder type(String type) {
        this.type = type;
        return this;
    }

    public String getPower() {
        return power;
    }

    public CarBuilder power(String power) {
        this.power = power;
        return this;
    }

    public String getComfort() {
        return comfort;
    }

    public CarBuilder comfort(String comfort) {
        this.comfort = comfort;
        return this;
    }

    @Override
    public String toString() {
        return "CarBuilder [type=" + type + ", power=" + power + ", comfort=" + comfort + "]";
    }

}
