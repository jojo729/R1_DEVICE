package com.unisound.b;

import com.unisound.vui.util.HttpUtils;
import okhttp3.Response;

import java.io.IOException;

public class f {
    final public static String b = "utf-8";
    public static java.util.HashMap c =  new java.util.HashMap();
    final private static int d = 10000;
    public String a;

    public f() {
        this.a = null;
    }

    public static com.unisound.b.l a(String s, java.util.Map a0, int i) {

        Response response = HttpUtils.getInstance().postSync(s,a0);
        try {
            String res = response.body().string();
            com.unisound.b.l a6 = new com.unisound.b.l();
            a6.a(com.unisound.b.j.a(response.header(("Date"))));
            a6.b(System.currentTimeMillis());
            a6.a(res);
            return a6;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String s, String s0, String s1) {
        java.net.HttpURLConnection a0 = null;
        Throwable a1 = null;
        java.io.BufferedReader a2 = null;
        java.io.PrintWriter a3 = null;
        StringBuilder a4 = new StringBuilder();
        label1: {
            label2: {
                Exception a5 = null;
                label4: {
                    label14: {
                        label13: {
                            try {
                                try {
                                    a0 = (java.net.HttpURLConnection)new java.net.URL(s).openConnection();
                                    break label14;
                                } catch(Exception a6) {
                                    a5 = a6;
                                }
                            } catch(Throwable a7) {
                                a1 = a7;
                                break label13;
                            }
                            a2 = null;
                            a0 = null;
                            a3 = null;
                            break label4;
                        }
                        a0 = null;
                        a3 = null;
                        a2 = null;
                        break label1;
                    }
                    label3: {
                        label12: {
                            label11: {
                                label9: {
                                    try {
                                        try {
                                            a0.setRequestProperty("accept", "*/*");
                                            a0.setRequestProperty("connection", "Keep-Alive");
                                            a0.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                                            a0.setConnectTimeout(10000);
                                            a0.setRequestMethod(s0);
                                            a0.setDoOutput(true);
                                            a0.setDoInput(true);
                                            boolean b0 = s0.equals((Object)"POST");
                                            label10: {
                                                if (b0) {
                                                    break label10;
                                                }
                                                a0.connect();
                                                a2 = new java.io.BufferedReader((java.io.Reader)new java.io.InputStreamReader(a0.getInputStream(), "UTF-8"));
                                                break label11;
                                            }
                                            a3 = new java.io.PrintWriter(a0.getOutputStream());
                                            break label12;
                                        } catch(Exception a8) {
                                            a5 = a8;
                                        }
                                    } catch(Throwable a9) {
                                        a1 = a9;
                                        break label9;
                                    }
                                    a2 = null;
                                    a3 = null;
                                    break label4;
                                }
                                a3 = null;
                                a2 = null;
                                break label1;
                            }
                            label8: {
                                label7: {
                                    try {
                                        try {
                                            while(true) {
                                                String s2 = a2.readLine();
                                                if (s2 == null) {
                                                    break label8;
                                                }
                                                a4.append(s2);
                                            }
                                        } catch(Exception a10) {
                                            a5 = a10;
                                        }
                                    } catch(Throwable a11) {
                                        a1 = a11;
                                        break label7;
                                    }
                                    a3 = null;
                                    break label4;
                                }
                                a3 = null;
                                break label1;
                            }
                            a3 = null;
                            break label3;
                        }
                        label6: {
                            label5: {
                                try {
                                    try {
                                        a3.print(s1);
                                        a3.flush();
                                        a2 = new java.io.BufferedReader((java.io.Reader)new java.io.InputStreamReader(a0.getInputStream(), "UTF-8"));
                                        break label6;
                                    } catch(Exception a12) {
                                        a5 = a12;
                                    }
                                } catch(Throwable a13) {
                                    a1 = a13;
                                    break label5;
                                }
                                a2 = null;
                                break label4;
                            }
                            a2 = null;
                            break label1;
                        }
                        {
                            try {
                                try {
                                    while(true) {
                                        String s3 = a2.readLine();
                                        if (s3 == null) {
                                            break label3;
                                        }
                                        a4.append(s3);
                                    }
                                } catch(Exception a14) {
                                    a5 = a14;
                                }
                            } catch(Throwable a15) {
                                a1 = a15;
                                break label1;
                            }
                            break label4;
                        }
                    }
                    try {
                        if (a3 != null) {
                            a3.close();
                        }
                        a2.close();
                    } catch(Exception a16) {
                        a16.printStackTrace();
                    }
                    if (a0 == null) {
                        break label2;
                    }
                    a0.disconnect();
                    break label2;
                }
                label0: {
                    try {
                        a5.printStackTrace();
                        break label0;
                    } catch(Throwable a17) {
                        a1 = a17;
                    }
                    break label1;
                }
                try {
                    if (a3 != null) {
                        a3.close();
                    }
                    if (a2 != null) {
                        a2.close();
                    }
                } catch(Exception a18) {
                    a18.printStackTrace();
                }
                if (a0 != null) {
                    a0.disconnect();
                }
            }
            return a4.toString();
        }
        try {
            if (a3 != null) {
                a3.close();
            }
            if (a2 != null) {
                a2.close();
            }
        } catch(Exception a19) {
            a19.printStackTrace();
        }
        if (a0 != null) {
            a0.disconnect();
        }
        return null;
    }

    private static String a(java.util.Map a0) {
        StringBuilder a1 = new StringBuilder();
        java.util.Iterator a2 = a0.entrySet().iterator();
        String s = "";
        Object a3 = a2;
        while(((java.util.Iterator)a3).hasNext()) {
            String s0 = null;
            Object a4 = ((java.util.Iterator)a3).next();
            a1.append(s);
            a1.append((String)((java.util.Map.Entry)a4).getKey());
            a1.append("=");
            try {
                s0 = java.net.URLEncoder.encode((String)((java.util.Map.Entry)a4).getValue(), "UTF-8");
            } catch(Exception a5) {
                a5.printStackTrace();
                com.unisound.b.i.b(new StringBuilder().append("encode error, key = ").append((String)((java.util.Map.Entry)a4).getKey()).toString());
                s0 = "";
            }
            a1.append(s0);
            s = "&";
        }
        com.unisound.b.i.d(new StringBuilder().append("requestData : POST params is ").append(a1.toString()).toString());
        return a1.toString();
    }

    public static void a(java.util.HashMap a0) {
        c = a0;
    }
}
