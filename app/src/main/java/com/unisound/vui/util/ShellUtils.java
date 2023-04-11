package com.unisound.vui.util;

import java.util.List;

public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int result2) {
            this.result = result2;
        }

        public CommandResult(int result2, String successMsg2, String errorMsg2) {
            this.result = result2;
            this.successMsg = successMsg2;
            this.errorMsg = errorMsg2;
        }

        public String toString() {
            return "CommandResult{result=" + this.result + ", successMsg='" + this.successMsg + '\'' + ", errorMsg='" + this.errorMsg + '\'' + '}';
        }
    }

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[]{command}, isRoot, true);
    }

    public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, true);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:0x012e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x012f, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0156, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0157, code lost:
        r1 = r5;
        r6 = null;
        r7 = null;
        r5 = r4;
        r4 = r0;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x015f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0160, code lost:
        r6 = null;
        r7 = null;
        r1 = r5;
        r5 = r4;
        r4 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0185, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0186, code lost:
        r1 = r5;
        r6 = null;
        r7 = null;
        r5 = r4;
        r4 = r0;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x018e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x018f, code lost:
        r6 = null;
        r7 = null;
        r1 = r5;
        r5 = r4;
        r4 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0056, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        r8.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ca, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00cf, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00d4, code lost:
        r8.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00f5, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00f6, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00fb, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x00fc, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x010b, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0110, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0115, code lost:
        r8.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0119, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x011a, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x012e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c A[SYNTHETIC, Splitter:B:25:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0051 A[Catch:{ IOException -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056 A[Catch:{ IOException -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c5 A[SYNTHETIC, Splitter:B:61:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ca A[Catch:{ IOException -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00cf A[Catch:{ IOException -> 0x00fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0106 A[SYNTHETIC, Splitter:B:88:0x0106] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x010b A[Catch:{ IOException -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0110 A[Catch:{ IOException -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x011e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.unisound.vui.util.ShellUtils.CommandResult execCommand(java.lang.String[] r11, boolean r12, boolean r13) {
        /*
        // Method dump skipped, instructions count: 423
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unisound.vui.util.ShellUtils.execCommand(java.lang.String[], boolean, boolean):com.unisound.vui.util.ShellUtils$CommandResult");
    }
}
