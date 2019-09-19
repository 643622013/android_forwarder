package com.example.zhys_pc.android_forwarder.http.entity;

public class LoginEntity {


    /**
     * token : ATapkvfifZF1IRvj_WNskZ4osvrwUQ6oXJ4
     * user : {"mobileAuthStatus":2,"companyName":null,"emailAuthStatusText":"未认证","personalType":4,"identityNo":null,"mobileAuthStatusText":"认证成功","qrCode":"","levelText":null,"legalPerson":null,"emailAuthStatus":0,"authType":1,"department":null,"email":"","qqIsBound":0,"qq":"","level":null,"companyType":null,"userSource":null,"nickName":null,"authStatus":1,"mobile":"18663927065","wechat":"","authStatusText":"认证中","ownerAuthStatus":15,"userName":"","userId":"190421300005","headPic":"http://118.190.162.225:8080/imageFiles/avatar/userHead/77C2/4509DC0A06F028AEFF0697988B12.png","personalTypeText":"司机","huanXinID":"","realName":"李磊","areaCode":null,"isViewFreight":null,"wechatisBound":0,"personalSignature":"","nativePlace":"山东省栖霞市翠屏街道城关村195号","ownerAuthStatusText":"认证成功","companyTypeText":null,"authTypeText":"个人用户"}
     */

    private String token;
    private UserBean user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * mobileAuthStatus : 2
         * companyName : null
         * emailAuthStatusText : 未认证
         * personalType : 4
         * identityNo : null
         * mobileAuthStatusText : 认证成功
         * qrCode :
         * levelText : null
         * legalPerson : null
         * emailAuthStatus : 0
         * authType : 1
         * department : null
         * email :
         * qqIsBound : 0
         * qq :
         * level : null
         * companyType : null
         * userSource : null
         * nickName : null
         * authStatus : 1
         * mobile : 18663927065
         * wechat :
         * authStatusText : 认证中
         * ownerAuthStatus : 15
         * userName :
         * userId : 190421300005
         * headPic : http://118.190.162.225:8080/imageFiles/avatar/userHead/77C2/4509DC0A06F028AEFF0697988B12.png
         * personalTypeText : 司机
         * huanXinID :
         * realName : 李磊
         * areaCode : null
         * isViewFreight : null
         * wechatisBound : 0
         * personalSignature :
         * nativePlace : 山东省栖霞市翠屏街道城关村195号
         * ownerAuthStatusText : 认证成功
         * companyTypeText : null
         * authTypeText : 个人用户
         */

        private int mobileAuthStatus;
        private Object companyName;
        private String emailAuthStatusText;
        private int personalType;
        private Object identityNo;
        private String mobileAuthStatusText;
        private String qrCode;
        private Object levelText;
        private Object legalPerson;
        private int emailAuthStatus;
        private int authType;
        private Object department;
        private String email;
        private int qqIsBound;
        private String qq;
        private Object level;
        private Object companyType;
        private Object userSource;
        private Object nickName;
        private int authStatus;
        private String mobile;
        private String wechat;
        private String authStatusText;
        private int ownerAuthStatus;
        private String userName;
        private String userId;
        private String headPic;
        private String personalTypeText;
        private String huanXinID;
        private String realName;
        private Object areaCode;
        private Object isViewFreight;
        private int wechatisBound;
        private String personalSignature;
        private String nativePlace;
        private String ownerAuthStatusText;
        private Object companyTypeText;
        private String authTypeText;

        public int getMobileAuthStatus() {
            return mobileAuthStatus;
        }

        public void setMobileAuthStatus(int mobileAuthStatus) {
            this.mobileAuthStatus = mobileAuthStatus;
        }

        public Object getCompanyName() {
            return companyName;
        }

        public void setCompanyName(Object companyName) {
            this.companyName = companyName;
        }

        public String getEmailAuthStatusText() {
            return emailAuthStatusText;
        }

        public void setEmailAuthStatusText(String emailAuthStatusText) {
            this.emailAuthStatusText = emailAuthStatusText;
        }

        public int getPersonalType() {
            return personalType;
        }

        public void setPersonalType(int personalType) {
            this.personalType = personalType;
        }

        public Object getIdentityNo() {
            return identityNo;
        }

        public void setIdentityNo(Object identityNo) {
            this.identityNo = identityNo;
        }

        public String getMobileAuthStatusText() {
            return mobileAuthStatusText;
        }

        public void setMobileAuthStatusText(String mobileAuthStatusText) {
            this.mobileAuthStatusText = mobileAuthStatusText;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public Object getLevelText() {
            return levelText;
        }

        public void setLevelText(Object levelText) {
            this.levelText = levelText;
        }

        public Object getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(Object legalPerson) {
            this.legalPerson = legalPerson;
        }

        public int getEmailAuthStatus() {
            return emailAuthStatus;
        }

        public void setEmailAuthStatus(int emailAuthStatus) {
            this.emailAuthStatus = emailAuthStatus;
        }

        public int getAuthType() {
            return authType;
        }

        public void setAuthType(int authType) {
            this.authType = authType;
        }

        public Object getDepartment() {
            return department;
        }

        public void setDepartment(Object department) {
            this.department = department;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getQqIsBound() {
            return qqIsBound;
        }

        public void setQqIsBound(int qqIsBound) {
            this.qqIsBound = qqIsBound;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getCompanyType() {
            return companyType;
        }

        public void setCompanyType(Object companyType) {
            this.companyType = companyType;
        }

        public Object getUserSource() {
            return userSource;
        }

        public void setUserSource(Object userSource) {
            this.userSource = userSource;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getAuthStatusText() {
            return authStatusText;
        }

        public void setAuthStatusText(String authStatusText) {
            this.authStatusText = authStatusText;
        }

        public int getOwnerAuthStatus() {
            return ownerAuthStatus;
        }

        public void setOwnerAuthStatus(int ownerAuthStatus) {
            this.ownerAuthStatus = ownerAuthStatus;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getPersonalTypeText() {
            return personalTypeText;
        }

        public void setPersonalTypeText(String personalTypeText) {
            this.personalTypeText = personalTypeText;
        }

        public String getHuanXinID() {
            return huanXinID;
        }

        public void setHuanXinID(String huanXinID) {
            this.huanXinID = huanXinID;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(Object areaCode) {
            this.areaCode = areaCode;
        }

        public Object getIsViewFreight() {
            return isViewFreight;
        }

        public void setIsViewFreight(Object isViewFreight) {
            this.isViewFreight = isViewFreight;
        }

        public int getWechatisBound() {
            return wechatisBound;
        }

        public void setWechatisBound(int wechatisBound) {
            this.wechatisBound = wechatisBound;
        }

        public String getPersonalSignature() {
            return personalSignature;
        }

        public void setPersonalSignature(String personalSignature) {
            this.personalSignature = personalSignature;
        }

        public String getNativePlace() {
            return nativePlace;
        }

        public void setNativePlace(String nativePlace) {
            this.nativePlace = nativePlace;
        }

        public String getOwnerAuthStatusText() {
            return ownerAuthStatusText;
        }

        public void setOwnerAuthStatusText(String ownerAuthStatusText) {
            this.ownerAuthStatusText = ownerAuthStatusText;
        }

        public Object getCompanyTypeText() {
            return companyTypeText;
        }

        public void setCompanyTypeText(Object companyTypeText) {
            this.companyTypeText = companyTypeText;
        }

        public String getAuthTypeText() {
            return authTypeText;
        }

        public void setAuthTypeText(String authTypeText) {
            this.authTypeText = authTypeText;
        }
    }
}
