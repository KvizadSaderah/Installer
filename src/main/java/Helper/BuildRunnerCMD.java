package Helper;

import data.Links;

    public class BuildRunnerCMD{

        private StringBuilder strBld;

        public BuildRunnerCMD setCCRunner(String value){
            strBld.append(" /cc-runner=" + value);
            return this;
        }

        public BuildRunnerCMD setCCCoid(String value){
            strBld.append(" /cc-coid=" + value);
            return this;
        }

        public BuildRunnerCMD setMiniProgress(boolean value){
            if(value){
                strBld.append(" /mini-progress=yes");
            } else {
                strBld.append(" /mini-progress=no");
            }
            return this;
        }

        public BuildRunnerCMD setUserClose(boolean value){
            if(value){
                strBld.append(" /user-close=yes");
            } else {
                strBld.append(" /user-close=no");
            }
            return this;
        }

        public BuildRunnerCMD setSysUserId(String value){
            strBld.append(" /sys-userid=\"" + value + "\"");
            return this;
        }

        public BuildRunnerCMD setSysPasswd(String value){
            strBld.append(" /sys-passwd=\"" + value + "\"");
            return this;
        }

        public BuildRunnerCMD setCCurl(String value){
            strBld.append(" /cc-url=" + value);
            return this;
        }

        public BuildRunnerCMD setCCcopin(String value){
            strBld.append(" /cc-copin=" + value);
            return this;
        }

        public BuildRunnerCMD setCCacctPwd(String value){
            strBld.append(" /cc-acct-pwd=" + value);
            return this;
        }

        public BuildRunnerCMD setCCallowBadCert(boolean value){
            if(value){
                strBld.append(" /cc-allow-bad-certs=yes");
            } else {
                strBld.append(" /cc-allow-bad-certs=no");
            }
            return this;
        }

        public BuildRunnerCMD setSilent(){
            strBld.append(" /S");
            return this;
        }

        public void executeCMD(){
            System.out.println(strBld.toString());
            GoodSyncHelper.exeCMD(strBld.toString());
        }

        public BuildRunnerCMD(){
            strBld = new StringBuilder();
            strBld.append("cmd /c " + "C:\\goodsync\\" + Links.goodsync_v10_CC_Runner_setup_pvt);
        }

    }

