package com.zlkj.zuixinstudio.bean;

import java.util.List;

/**
 * Created by gyl on 2017/3/30.
 */

public class HomeRequset {


    /**
     * status : 100
     * msgs : 请求成功
     * datas : {"index":{"advList":[{"advName":"海量商品","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070106124259.jpg","advLink":""},{"advName":"店主招聘","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409553746.jpg","advLink":"http://www.shangxiaowang.cc/api.php/index/dengji"},{"advName":"推广知了币","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409562029.jpg","advLink":"http://www.shangxiaowang.cc//api.php/user/userYaoReg?userTuiCode=7459021&from=timeline&isappinstalled=1"},{"advName":"购物节","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409563778.png","advLink":""},{"advName":"加入我们","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070402334830.png","advLink":"http://www.shangxiaowang.cc/api.php/index/dengji"}],"userRechargeList":[{"content":"用户s4rer46w 微信成功充值了10.00知了币"},{"content":"用户mzaypeja 微信成功充值了5.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了5.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了10.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了200.00知了币"}],"goodsList":[{"goodsId":"2057","goodsTitle":"花姐墨墨香500g现磨黑芝麻糊五谷杂粮羹主妇干货营养早餐冲饮包邮","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062604452813.jpg","goodsCanMoney":"56.28","goodsMoney":"46.90"},{"goodsId":"1005","goodsTitle":"HanGee洗眼液清洁护理液舒缓眼部清凉保养眼睛100ml 2送美瞳盒","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060802544519.jpg","goodsCanMoney":"20.40","goodsMoney":"17.00"},{"goodsId":"1432","goodsTitle":"【卫龙旗舰店】大面筋106g*5 卫龙辣条零食麻辣辣片小吃特","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017061406252222.png","goodsCanMoney":"26.28","goodsMoney":"21.90"},{"goodsId":"2429","goodsTitle":"原创古风手帐本笔记本","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062911111834.jpg","goodsCanMoney":"0.00","goodsMoney":"26.00"},{"goodsId":"492","goodsTitle":"唯美梦幻系列圆形便利贴 留言记事办公学习彩色星空N次贴 30张*4","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060704314999.jpg","goodsCanMoney":"9.00","goodsMoney":"7.50"},{"goodsId":"1128","goodsTitle":"创意早餐杯子陶瓷情侣杯家用水杯马克杯定制带盖咖啡杯办公茶杯","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060902371541.jpg","goodsCanMoney":"30.60","goodsMoney":"25.50"},{"goodsId":"2445","goodsTitle":"红双喜羽毛球拍双拍家庭初学超轻2支装初学进攻型正品羽毛拍ymqp","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062902293137.jpg","goodsCanMoney":"47.88","goodsMoney":"41.90"},{"goodsId":"2396","goodsTitle":"辉胜精品三星级多球训练练习用乒乓球 比赛专用乒乓球 兵乓球","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062805594884.jpg","goodsCanMoney":"98.40","goodsMoney":"82.00"}],"shopCount":"0"}}
     */

    private int status;
    private String msgs;
    private DatasBean datas;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsgs() {
        return msgs;
    }

    public void setMsgs(String msgs) {
        this.msgs = msgs;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * index : {"advList":[{"advName":"海量商品","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070106124259.jpg","advLink":""},{"advName":"店主招聘","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409553746.jpg","advLink":"http://www.shangxiaowang.cc/api.php/index/dengji"},{"advName":"推广知了币","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409562029.jpg","advLink":"http://www.shangxiaowang.cc//api.php/user/userYaoReg?userTuiCode=7459021&from=timeline&isappinstalled=1"},{"advName":"购物节","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409563778.png","advLink":""},{"advName":"加入我们","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070402334830.png","advLink":"http://www.shangxiaowang.cc/api.php/index/dengji"}],"userRechargeList":[{"content":"用户s4rer46w 微信成功充值了10.00知了币"},{"content":"用户mzaypeja 微信成功充值了5.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了5.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了10.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了200.00知了币"}],"goodsList":[{"goodsId":"2057","goodsTitle":"花姐墨墨香500g现磨黑芝麻糊五谷杂粮羹主妇干货营养早餐冲饮包邮","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062604452813.jpg","goodsCanMoney":"56.28","goodsMoney":"46.90"},{"goodsId":"1005","goodsTitle":"HanGee洗眼液清洁护理液舒缓眼部清凉保养眼睛100ml 2送美瞳盒","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060802544519.jpg","goodsCanMoney":"20.40","goodsMoney":"17.00"},{"goodsId":"1432","goodsTitle":"【卫龙旗舰店】大面筋106g*5 卫龙辣条零食麻辣辣片小吃特","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017061406252222.png","goodsCanMoney":"26.28","goodsMoney":"21.90"},{"goodsId":"2429","goodsTitle":"原创古风手帐本笔记本","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062911111834.jpg","goodsCanMoney":"0.00","goodsMoney":"26.00"},{"goodsId":"492","goodsTitle":"唯美梦幻系列圆形便利贴 留言记事办公学习彩色星空N次贴 30张*4","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060704314999.jpg","goodsCanMoney":"9.00","goodsMoney":"7.50"},{"goodsId":"1128","goodsTitle":"创意早餐杯子陶瓷情侣杯家用水杯马克杯定制带盖咖啡杯办公茶杯","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060902371541.jpg","goodsCanMoney":"30.60","goodsMoney":"25.50"},{"goodsId":"2445","goodsTitle":"红双喜羽毛球拍双拍家庭初学超轻2支装初学进攻型正品羽毛拍ymqp","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062902293137.jpg","goodsCanMoney":"47.88","goodsMoney":"41.90"},{"goodsId":"2396","goodsTitle":"辉胜精品三星级多球训练练习用乒乓球 比赛专用乒乓球 兵乓球","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062805594884.jpg","goodsCanMoney":"98.40","goodsMoney":"82.00"}],"shopCount":"0"}
         */

        private IndexBean index;

        public IndexBean getIndex() {
            return index;
        }

        public void setIndex(IndexBean index) {
            this.index = index;
        }

        public static class IndexBean {
            /**
             * advList : [{"advName":"海量商品","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070106124259.jpg","advLink":""},{"advName":"店主招聘","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409553746.jpg","advLink":"http://www.shangxiaowang.cc/api.php/index/dengji"},{"advName":"推广知了币","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409562029.jpg","advLink":"http://www.shangxiaowang.cc//api.php/user/userYaoReg?userTuiCode=7459021&from=timeline&isappinstalled=1"},{"advName":"购物节","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070409563778.png","advLink":""},{"advName":"加入我们","advImg":"http://www.shangxiaowang.cc//upload/adv/2017070402334830.png","advLink":"http://www.shangxiaowang.cc/api.php/index/dengji"}]
             * userRechargeList : [{"content":"用户s4rer46w 微信成功充值了10.00知了币"},{"content":"用户mzaypeja 微信成功充值了5.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了5.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了10.00知了币"},{"content":"用户5c3x7s7j 微信成功充值了200.00知了币"}]
             * goodsList : [{"goodsId":"2057","goodsTitle":"花姐墨墨香500g现磨黑芝麻糊五谷杂粮羹主妇干货营养早餐冲饮包邮","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062604452813.jpg","goodsCanMoney":"56.28","goodsMoney":"46.90"},{"goodsId":"1005","goodsTitle":"HanGee洗眼液清洁护理液舒缓眼部清凉保养眼睛100ml 2送美瞳盒","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060802544519.jpg","goodsCanMoney":"20.40","goodsMoney":"17.00"},{"goodsId":"1432","goodsTitle":"【卫龙旗舰店】大面筋106g*5 卫龙辣条零食麻辣辣片小吃特","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017061406252222.png","goodsCanMoney":"26.28","goodsMoney":"21.90"},{"goodsId":"2429","goodsTitle":"原创古风手帐本笔记本","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062911111834.jpg","goodsCanMoney":"0.00","goodsMoney":"26.00"},{"goodsId":"492","goodsTitle":"唯美梦幻系列圆形便利贴 留言记事办公学习彩色星空N次贴 30张*4","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060704314999.jpg","goodsCanMoney":"9.00","goodsMoney":"7.50"},{"goodsId":"1128","goodsTitle":"创意早餐杯子陶瓷情侣杯家用水杯马克杯定制带盖咖啡杯办公茶杯","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017060902371541.jpg","goodsCanMoney":"30.60","goodsMoney":"25.50"},{"goodsId":"2445","goodsTitle":"红双喜羽毛球拍双拍家庭初学超轻2支装初学进攻型正品羽毛拍ymqp","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062902293137.jpg","goodsCanMoney":"47.88","goodsMoney":"41.90"},{"goodsId":"2396","goodsTitle":"辉胜精品三星级多球训练练习用乒乓球 比赛专用乒乓球 兵乓球","goodsMainPic":"http://www.shangxiaowang.cc//upload/hgoods/2017062805594884.jpg","goodsCanMoney":"98.40","goodsMoney":"82.00"}]
             * shopCount : 0
             */

            private String shopCount;
            private List<AdvListBean> advList;
            private List<UserRechargeListBean> userRechargeList;
            private List<GoodsListBean> goodsList;

            public String getShopCount() {
                return shopCount;
            }

            public void setShopCount(String shopCount) {
                this.shopCount = shopCount;
            }

            public List<AdvListBean> getAdvList() {
                return advList;
            }

            public void setAdvList(List<AdvListBean> advList) {
                this.advList = advList;
            }

            public List<UserRechargeListBean> getUserRechargeList() {
                return userRechargeList;
            }

            public void setUserRechargeList(List<UserRechargeListBean> userRechargeList) {
                this.userRechargeList = userRechargeList;
            }

            public List<GoodsListBean> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<GoodsListBean> goodsList) {
                this.goodsList = goodsList;
            }

            public static class AdvListBean {
                /**
                 * advName : 海量商品
                 * advImg : http://www.shangxiaowang.cc//upload/adv/2017070106124259.jpg
                 * advLink :
                 */

                private String advName;
                private String advImg;
                private String advLink;

                public String getAdvName() {
                    return advName;
                }

                public void setAdvName(String advName) {
                    this.advName = advName;
                }

                public String getAdvImg() {
                    return advImg;
                }

                public void setAdvImg(String advImg) {
                    this.advImg = advImg;
                }

                public String getAdvLink() {
                    return advLink;
                }

                public void setAdvLink(String advLink) {
                    this.advLink = advLink;
                }
            }

            public static class UserRechargeListBean {
                /**
                 * content : 用户s4rer46w 微信成功充值了10.00知了币
                 */

                private String content;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }

            public static class GoodsListBean {
                /**
                 * goodsId : 2057
                 * goodsTitle : 花姐墨墨香500g现磨黑芝麻糊五谷杂粮羹主妇干货营养早餐冲饮包邮
                 * goodsMainPic : http://www.shangxiaowang.cc//upload/hgoods/2017062604452813.jpg
                 * goodsCanMoney : 56.28
                 * goodsMoney : 46.90
                 */

                private String goodsId;
                private String goodsTitle;
                private String goodsMainPic;
                private String goodsCanMoney;
                private String goodsMoney;

                public String getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
                }

                public String getGoodsTitle() {
                    return goodsTitle;
                }

                public void setGoodsTitle(String goodsTitle) {
                    this.goodsTitle = goodsTitle;
                }

                public String getGoodsMainPic() {
                    return goodsMainPic;
                }

                public void setGoodsMainPic(String goodsMainPic) {
                    this.goodsMainPic = goodsMainPic;
                }

                public String getGoodsCanMoney() {
                    return goodsCanMoney;
                }

                public void setGoodsCanMoney(String goodsCanMoney) {
                    this.goodsCanMoney = goodsCanMoney;
                }

                public String getGoodsMoney() {
                    return goodsMoney;
                }

                public void setGoodsMoney(String goodsMoney) {
                    this.goodsMoney = goodsMoney;
                }
            }
        }
    }
}
