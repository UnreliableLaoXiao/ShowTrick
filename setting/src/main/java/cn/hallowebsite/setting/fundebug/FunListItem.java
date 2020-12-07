package cn.hallowebsite.setting.fundebug;

public class FunListItem {

    public FunListItem() {
    }

    public FunListItem(String title, String mes, int iconId, String routerPath) {
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
