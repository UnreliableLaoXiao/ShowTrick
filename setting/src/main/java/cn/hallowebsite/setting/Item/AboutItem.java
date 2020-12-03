package cn.hallowebsite.setting.Item;

public class AboutItem {

    public AboutItem() {
    }

    public AboutItem(String title, int iconId, String routerPath, String mes) {
        this.title = title;
        this.mes = mes;
        this.iconId = iconId;
        this.routerPath = routerPath;
    }

    public String title;
    public String mes;
    public int iconId;
    public String routerPath;

    @Override
    public String toString() {
        return "AboutItem{" +
                "title='" + title + '\'' +
                ", mes='" + mes + '\'' +
                ", iconId=" + iconId +
                ", routerPath='" + routerPath + '\'' +
                '}';
    }
}
