package mine.com.helper.scale;

import android.view.View;
import android.widget.ImageView;

import mine.com.helper.rebound.RoundFactory;
import mine.com.helper.rebound.RoundAll;

/**
 * Created by qiuyouzone on 2018/3/22.
 *
 * 对应的a
 */

class WindowAttributeMatch {
    private String[] attribute;
    public WindowAttributeMatch() {
    }

    void d(String c, View b, WindowLoadViewImpl d, RoundAll f) {
        if(c != null && !c.equals("")) {
            if(c.contains("%s")) {
                f.b(RoundFactory.c(b, 1.0F));
            } else if(c.contains("%k")) {
                f.b(RoundFactory.e(b, 1.0F));
            } else if(c.contains("%v")) {
                f.b(RoundFactory.b(b, -b.getWidth()));
            } else if(c.contains("%r")) {
                f.b(RoundFactory.b(b, d.height));
            } else if(c.contains("%p")) {
                f.b(RoundFactory.h(b, -b.getHeight()));
            } else if(c.contains("%b")) {
                f.b(RoundFactory.h(b, d.width));
            }

            if((this.attribute = this.c(c, "(", ")")) != null) {
                try {
                    if(this.attribute.length == 3) {
                        d.loadViewWidthHeightSize(b, this.b(this.attribute[0]), this.b(this.attribute[1]), this.b(this.attribute[2]));
                    } else if(this.attribute.length == 2) {
                        d.loadViewWidthHeight(b, this.b(this.attribute[0]), this.b(this.attribute[1]));
                    } else {
                        d.loadViewSize(b, this.b(this.attribute[0]));
                    }
                } catch (Exception var9) {
                    ;
                }
            }

            if((this.attribute = this.c(c, "[", "]")) != null) {
                try {
                    d.loadViewPadding(b, this.b(this.attribute[0]), this.b(this.attribute[1]), this.b(this.attribute[2]), this.b(this.attribute[3]));
                } catch (Exception var8) {
                    ;
                }
            }

            if((this.attribute = this.c(c, "{", "}")) != null) {
                try {
                    d.loadViewMargin(b, this.b(this.attribute[0]), this.b(this.attribute[1]), this.b(this.attribute[2]), this.b(this.attribute[3]));
                } catch (Exception var7) {
                    ;
                }
            }

            if((this.attribute = this.c(c, "/", "\\")) != null) {
                try {
                    String[] g;
                    d.loadViewMinMax(b, this.b((g = this.attribute[0].split("-"))[0]), this.b(g[1]), this.b((g = this.attribute[1].split("-"))[0]), this.b(g[1]));
                } catch (Exception var6) {
                    ;
                }
            }

            if(b instanceof ImageView) {
                b.setTag((Object)null);
            }
        }

    }

    private int b(String s) {
        return Integer.parseInt(s);
    }

    private String[] c(String c, String s, String e) {
        return c.contains(s) && c.contains(e)?c.substring(c.indexOf(s) + 1, c.indexOf(e)).split(","):null;
    }
}
