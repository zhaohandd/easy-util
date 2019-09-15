package zju.edu.als.util;

/**
 * @author imddon
 * @create 2019-09-13 20:08
 */
public enum Executors {
    PYTHON,
    PYTHON3,
    GENERAL;

    private Executors() {
    }

    public String getId() {
        return this.name().toLowerCase();
    }

    public static Executors fromId(String id) {
        try {
            return valueOf(id.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
