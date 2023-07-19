package com.PoetsCorner;

public class Calculator {
    private static boolean divbyzero=false;
    public static String calculate(String s) {return calculate(s, 30);}
    public static String calculate(String s, int length)
    {
        //if (length <0) {length=30;}
        length = Math.abs(length); // Handle possible negative numbers being passed
        String a,b,u,c;
        s=s.replace("{","(").replace("[","(").replace("}",")").replace("]",")");
        s=s.replace(" ","").replace(",",".").replace("�","*").replace("�","/").replace((char)92,'/');
        while(CounterSubstring(s,"..")>0||CounterSubstring(s,"**")>0||CounterSubstring(s,"//")>0)
            {s=s.replace("..",".").replace("**","*").replace("//","/");}
        s=s.replace("+*","+").replace("+/","+").replace("-*","-").replace("-/","-");
        while(CounterSubstring(s,"++")>0||CounterSubstring(s,"+-")>0||CounterSubstring(s,"-+")>0||CounterSubstring(s,"--")>0)
            {s=s.replace("--","+").replace("-+","-").replace("+-","-").replace("++","+");}
        s=s.replace("*+","*").replace("/+","/");
        s=s.replace("*-","🇪🇬").replace("/-","🇸🇦");
        s=s.replace("+","\n").replace("-","\n"+"-");
        s=s.replace("🇪🇬","*-").replace("🇸🇦","/-");
        a="0"; divbyzero=false;
        for (int i=0; i<=CounterSubstring(s,"\n");i++) {
            b=Line(s,i);
            if (CounterSubstring(b,"*")>0||CounterSubstring(b,"/")>0) {
                b=b.replace("*","\n"+"*").replace("/","\n"+"/");
                c=Line(b,0);
                for (int j=1; j<=CounterSubstring(b,"\n"); j++) {
                    if (CounterSubstring(Line(b,j),"*")==1) {c+="\n"+ Line(b,j);}
                }
                for (int j=1; j<=CounterSubstring(b,"\n"); j++) {
                    if (CounterSubstring(Line(b,j),"/")==1) {c+="\n"+ Line(b,j);}
                }
                b=Line(c,0);
                for (int j=1; j<=CounterSubstring(c,"\n"); j++) {
                    u=Line(c,j);
                    if (CounterSubstring(u,"/")==1) {
                        u=u.replace("/","");
                        b=Divide(b,u,length);
                    }else {
                        u=u.replace("*","");
                        b=Multi(b,u);
                    }
                }
            }
            a=Sum(a,b);
        }
        if (divbyzero) {return "Cannot divide by zero";} else {return a;}
    }

    public static String Sum(String a, String b){
        String c="0";
        int x, y, z=0;
        boolean l1=false,l2=false,l3;
        if (SubstringPlace(a,"-",0)==0){l1=true; a=a.substring(1);}
        if (SubstringPlace(b,"-",0)==0){l2=true; b=b.substring(1);}
        if (CounterSubstring(a,".")==0) {a+=".0";}
        if (CounterSubstring(b,".")==0) {b+=".0";}
        while(SubstringPlace(a,".",0)<SubstringPlace(b,".",0)){a="0"+a;}
        while(SubstringPlace(b,".",0)<SubstringPlace(a,".",0)){b="0"+b;}
        while(a.length()<b.length()){a+="0";}
        while(b.length()<a.length()){b+="0";}
        a="0"+a; b="0"+b;
        if ((l1 ^ l2)&& !big(a,b)) {c=a;l3=l1; a=b;l1=l2; b=c;l2=l3; c="0";}
        for (int i=a.length()-1; i>=0;i--){
            if (a.charAt(i) == '.') {c="."+c; continue;}
            x=(int)num(a.substring(i,i+1));
            y=(int)num(b.substring(i,i+1));
            if (l1==l2) {
                z=x+y+z;
                if (z>9){
                    z-=10;
                    c=z+c; z=1;
                }
                else {c=z+c; z=0;}
            } else {
                z=x-y+z;
                if (z<0){
                    z+=10;
                    c=z+c; z=-1;
                }
                else {c=z+c; z=0;}
            }
        }
        z=c.length();
        while(c.charAt(z - 1) == '0'){c=c.substring(0,z-1); z--;}
        if (c.charAt(z - 1) == '.') {c=c.substring(0,z-1); /*z--;*/}
        while(c.charAt(0) == '0' &&c.length()>1){c=c.substring(1);}
        if (c.charAt(0) == '.') {c="0"+c;}
        if (!c.equals("0")&&l1){c="-"+c;}
        return c;
    }
    public static boolean big(String a, String b){
        boolean d=true;
        int x,y;
        for(int i=0;i<a.length();i++){
            x=(int)num(a.substring(i,i+1));
            y=(int)num(b.substring(i,i+1));
            if (x<y) {d=false; break;}
            else if (x>y) {break;}
        }
        return d;
    }

    public static String Multi(String a, String b){
        String s="",c="";
        int x=0, y, z=0;
        boolean l3=false;
        if (SubstringPlace(a,"-",0)==0){l3=true; a=a.substring(1);}
        if (SubstringPlace(b,"-",0)==0){l3=!l3; b=b.substring(1);}
        if (b.length()>a.length()){s=a; a=b; b=s; s="";}
        a="0"+a;
        for (int j=b.length()-1; j>=0;j--){
            if (b.charAt(j) == '.') {continue;}
            for (int i=a.length()-1; i>=0;i--){
                if (a.charAt(i) == '.') {x=-1; continue;}
                x=(int)num(a.substring(i,i+1));
                y=(int)num(b.substring(j,j+1));
                z=x*y +z;
                if (z>9){
                    y=(int)num((z+"").substring((z+"").length()-1));
                    z=(z-y)/10;
                    c=y+c;
                }
                else {c=z+c;z=0;}
            }
            if (x!=-1){
                s+=c; c="";
                if (j!=0){s+="\n";}
            }
        }
        z=0;
        x=SubstringPlace(a,".",0);
        if (x!=-1){z+=a.length()-x-1;}
        x=SubstringPlace(b,".",0);
        if (x!=-1){z+=b.length()-x-1;}
        a="0";
        for (int i=0; i<=CounterSubstring(s,"\n");i++) {
            b=Line(s,i);
            for (int j=0; j<i; j++) {b+="0";}
            a=Sum(a,b);
        }
        c=StuffingString(a,".",a.length()-z);
        z=c.length();
        while(c.charAt(z - 1) == '0'){c=c.substring(0,z-1);z--;}
        if (c.charAt(z - 1) == '.') {c=c.substring(0,z-1); /*z--;*/}
        while(c.charAt(0) == '0' &&c.length()>1){c=c.substring(1);}
        if (c.charAt(0) == '.') {c="0"+c;}
        if (!c.equals("0")&&l3){c="-"+c;}
        return c;
    }

    public static String Divide(String a, String b, int length){
        String s="",c="0",f; int w=0,z;
        boolean l3=false;
        if (SubstringPlace(a,"-",0)==0){l3=true; a=a.substring(1);}
        if (SubstringPlace(b,"-",0)==0){l3=!l3; b=b.substring(1);}
        a=Sum(a,"0"); b=Sum(b,"0");
        if (b.equals("0")) {divbyzero=true;}
        else if (b.equals("1")) {s=a;}
        else if (a.equals("0")) {s="0";}
        else {
            z= SubstringPlace(a,".",0);
            if (z==-1) {w+=a.length();} else {w+=z;}
            z= SubstringPlace(b,".",0);
            if (z==-1) {w-=b.length();} else {w-=z;}
            w--;
            if (w>0) {for (int i=0; i<w; i++) {b=Multi(b,"10");}} else {w=0;}
            f=Sum(a,"-"+b);
            while(f.charAt(0) != '-') {
                c=Sum(c,"1"); a=f; f=Sum(a,"-"+b);
            }
            s=c; z=0;
            while (!Sum(a,"0").equals("0")&&z<length+w){
                if (z==0) {s+=".";}
                c="0";
                z+=1;
                a=Multi(a,"10");
                f=Sum(a,"-"+b);
                while (f.charAt(0) != '-') {
                    c=Sum(c,"1"); a=f; f=Sum(a,"-"+b);
                }
                s+=c;
            }
            for (int i=0; i<w; i++) {s=Multi(s,"10");}
            if (!s.equals("0")&&l3){s="-"+s;}
        }
        return s;
    }

    public static String StuffingString(String s, String c, int place){
        if (place<0) {place=0;} else if (place>s.length()) {place=s.length();}
        return s.substring(0,place)+c+s.substring(place);
    }
    public static int CounterSubstring(String s, String c){
        int x=0,y=c.length();
        for (int i=0;i<=s.length()-y;i++){
            if (s.substring(i,i+y).equals(c)){x++;}
        }
        return x;
    }
    public static int SubstringPlace(String s, String c, int n){
        int x=-1,y=c.length(),n1=-1;
        if (n>=0){
            for (int i=0;i<=s.length()-y;i++){
                if (s.substring(i,i+y).equals(c)){
                    n1++;
                    if (n1==n){x=i; break;}
                }
            }
        }
        return x;
    }
    public static String Line(String s, int ln){
        String c="";
        int n=CounterSubstring(s,"\n"), beginIndex=0, endIndex=s.length();
        if (ln>=0 && n>0 && ln<=n){
            if (ln==0) {endIndex= SubstringPlace(s,"\n",0);}
            else if (ln==n){beginIndex= SubstringPlace(s,"\n",n-1)+1;}
            else {
                beginIndex=SubstringPlace(s,"\n",ln-1)+1;
                endIndex = SubstringPlace(s,"\n",ln);
            }
            c=s.substring(beginIndex,endIndex);
        }
        else if (ln==0){c=s;}
        return c;
    }

    /* not used
    public static String Line(String s, int ln1,int ln2){
        String c="";
        int n=CounterSubstring(s,"\n");
        if (ln1<0) {ln1=0;}
        if (ln2>n) {ln2=n;}
        if (ln1<=ln2) {
            for (int i=ln1; i<=ln2; i++) {
                c+=Line(s,i);
                if (i!=ln2) {c+="\n";}
            }
        }
        return c;
    } */

    public static String ArabicNumber(String s){
        String c,s1="";
        if (!s.equals("")) {
            for (int i=0; i<s.length();i++) {
                c=s.substring(i,i+1);
                if (c.equals("0")){s1+="0";}
                else if (c.equals("1")){s1+="1";}
                else if (c.equals("2")){s1+="2";}
                else if (c.equals("3")){s1+="3";}
                else if (c.equals("4")){s1+="4";}
                else if (c.equals("5")){s1+="5";}
                else if (c.equals("6")){s1+="6";}
                else if (c.equals("7")){s1+="7";}
                else if (c.equals("8")){s1+="8";}
                else if (c.equals("9")){s1+="9";}
                else if (c.equals(",")){s1+=".";}
                else {s1+=c;}
            }
        }
        return s1;
    }
    public static double num(String s){
        s=ArabicNumber(s);
        double x, y=0;
        int z=0;
        char c;
        if (!s.equals("")&&CounterSubstring(s,".")<=1) {
            for (int i=s.length()-1; i>=0;i--) {
                c=s.charAt(i);
                x=Math.pow(10,z);
                if (i==0 && c == '+' || c == '0'){}
                else if (c == '1'){y+=1*x;}
                else if (c == '2'){y+=2*x;}
                else if (c == '3'){y+=3*x;}
                else if (c == '4'){y+=4*x;}
                else if (c == '5'){y+=5*x;}
                else if (c == '6'){y+=6*x;}
                else if (c == '7'){y+=7*x;}
                else if (c == '8'){y+=8*x;}
                else if (c == '9'){y+=9*x;}
                else if (c == '.'){y*=Math.pow(10,-z);z=-1;}
                else if (i==0 && c == '-'){y*=-1;}
                else {y=0; break;}
                z++;
            }
        }
        return y;
    }
}
