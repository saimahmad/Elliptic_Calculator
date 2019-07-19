package com.saimdev.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;

class Legendre {








    public static int symbol(BigInteger a, BigInteger p) {
        if (a.remainder(p).equals(BigInteger.ZERO)) {
            return 0;
        }


        BigInteger exponent = p.subtract(BigInteger.ONE);
        exponent = exponent.divide(BigInteger.valueOf(2));
        BigInteger result = a.modPow(exponent, p); // 1 <= result <= p - 1


        if (result.equals(BigInteger.ONE)) {
            return 1;
        } else if (result.equals(p.subtract(BigInteger.ONE))) {
            return -1;
        } else {
            throw new ArithmeticException("Error computing the Legendre symbol.");
        }
    }
}

public class MainActivity extends AppCompatActivity {

    private Button add;
    private Button sub;
    private Button mul;
    private Button dub;
    private Button porder;
    private Button corder;
    private Button gen;
    private Button fpoints;
    private Button fy;
    private Button clr;

    private EditText ap;
    private EditText bp;
    private EditText pp;
    private EditText kp;
    private EditText x1;
    private EditText y1;
    private EditText x2;
    private EditText y2;

    private TextView result;
  //  result.setMovementMethod(new ScrollingMovementMethod());

    public static int cnt=0;
    public static BigInteger ORD = BigInteger.ONE.negate();
    public static BigInteger order = BigInteger.ZERO;
    public static BigInteger xr = BigInteger.ZERO,yr = BigInteger.ZERO,mtt = BigInteger.ZERO;
    public static int pt=0;
    public static String memory;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             /*   int x = Integer.parseInt(a.getText().toString());
                int y = Integer.parseInt(b.getText().toString());
                result.setText("Result = ");
                result.append(Integer.toString(x+y));
                */

                result.scrollTo(0,0);
                System.out.println("Add function here!");
                result.setText("RESULT : ADD");
                BigInteger resu[] = new BigInteger[2];
                BigInteger point1,point2,point3,point4,mt,at,bt;

                try{
                    point1=new BigInteger(x1.getText().toString());
                    point2=new BigInteger(y1.getText().toString());
                    point3=new BigInteger(x2.getText().toString());
                    point4 = new BigInteger(y2.getText().toString());
                    mt = new BigInteger(pp.getText().toString());
                    at = new BigInteger(ap.getText().toString());
                    bt=  new BigInteger(bp.getText().toString());
                    if(point2.multiply(point2).mod(mt).equals(point1.multiply(point1).multiply(point1)))
                        throw new Exception();
                }
                catch(Exception e ){
                    result.setText("PLEASE ENTER PROPER POINTS");
                    return;}

                if(!mt.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }

                resu = addition(point1,point2,point3,point4,mt,at);
                if(resu[0].toString().equals("-1")&&(resu[1].toString().equals("-1")))
                {  result.setText("Addition not defined");  return;}
                //   if(!(check(at,bt,mt,point1,point2)&&check(at,bt,mt,point3,point4)))
                //  {res.setText("please inter proper inputs");  return;}
                result.setText("RESULT: ("+resu[0].toString()+" , "+resu[1].toString()+")");
            }
        });



        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result.scrollTo(0,0);
                System.out.println("Subtract function here!");
                result.setText("RESULT : SUB");
                BigInteger resu[] = new BigInteger[2];

                BigInteger point1,point2,point3,point4,mt,at,bt;

                try{
                    point1=new BigInteger(x1.getText().toString());
                    point2=new BigInteger(y1.getText().toString());
                    point3=new BigInteger(x2.getText().toString());
                    point4 = new BigInteger(y2.getText().toString());
                    mt = new BigInteger(pp.getText().toString());
                    at = new BigInteger(ap.getText().toString());
                    bt = new BigInteger(ap.getText().toString());
                }
                catch(Exception e ){
                    result.setText("PLEASE ENTER PROPER POINTS");
                    return;}
                if(!mt.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }

                resu = addition(point1,point2,point3,point4.negate(),mt,at);
                if(resu[0].toString().equals("-1")&&(resu[1].toString().equals("-1")))
                {result.setText("Subtraction  not defined");  return ;}

                //if(!(check(at,bt,mt,point1,point2)&&check(at,bt,mt,point3,point4)))
                // {res.setText("please inter proper inputs");  return;}
                result.setText("RESULT: ("+resu[0].toString()+" , "+resu[1].toString()+")");
            }
            });




        mul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                result.scrollTo(0,0);
                System.out.println("Multiply function here!");
                result.setText("RESULT : MUL");
                BigInteger nx=BigInteger.ZERO,np,nq,point1,point2,p,ny=BigInteger.ZERO,k = BigInteger.ZERO,mt,i = BigInteger.ONE.add(BigInteger.ONE);
                BigInteger resu[] = new BigInteger[2];


                try{
                    point1=new BigInteger(ap.getText().toString());
                    point2=new BigInteger(bp.getText().toString());
                    mt=new BigInteger(pp.getText().toString());
                    nx=new BigInteger(x1.getText().toString());
                    ny=new BigInteger(y1.getText().toString());
                    k=new BigInteger(kp.getText().toString());
                }
                catch(Exception e ){
                    result.setText("PLEASE ENTER PROPER POINTS");
                    return;}

                if(!mt.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }
                resu[0] = nx;
                resu[1] = ny;
                for( ; i.compareTo(k) <= 0 ; i = i.add(BigInteger.ONE))
                {
                    if((resu[0].toString()).equals(new String("-1"))||((resu[1].toString()).equals(new String("-1"))))
                    {  System.out.println("Error"); break;}

                    resu = addition(nx,ny,resu[0],resu[1],mt,point1);
                    System.out.println(":"+resu[0]+resu[1]+":");
                }

                if(i.compareTo(k)<=0)  {result.setText("Multiplication is undefinded");  return ;}
                result.setText("RESULT : ( "+resu[0].toString()+", "+resu[1].toString()+" )");

            }
            });




        dub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result.scrollTo(0,0);
                System.out.println("Double function here!");
                result.setText("RESULT : DOUBLE");
                BigInteger resu[] = new BigInteger[2];

                BigInteger point1,point2,mt,at;
                xr=BigInteger.ZERO;
                yr=BigInteger.ZERO;

                try{
                    point1=new BigInteger(x1.getText().toString());
                    point2=new BigInteger(y1.getText().toString());

                    mt = new BigInteger(pp.getText().toString());
                    at = new BigInteger(ap.getText().toString());
                }
                catch(Exception e ){
                    result.setText("PLEASE ENTER PROPER POINTS");
                    return;}

                if(!mt.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }
                //resu = addition(point1,point2,point1,point2,mt,at);
                double_point(at,BigInteger.ZERO,mt,point1,point2,xr,yr);

              //  result.setText("RESULT: ("+resu[0].toString()+" , "+resu[1].toString()+")");
                result.setText("RESULT: ("+xr.toString()+" , "+yr.toString()+")");

            }
            });




        porder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result.scrollTo(0,0);
                BigInteger xq=BigInteger.ZERO,yq=BigInteger.ZERO,c;
                BigInteger a,b,p,xp,yp;
                order = BigInteger.ZERO;
                try{ a= new BigInteger(ap.getText().toString());
                    b= new BigInteger(bp.getText().toString());
                    mtt = new BigInteger(pp.getText().toString());
                    xp=new BigInteger(x1.getText().toString());
                    yp=new BigInteger(y1.getText().toString());
                }
                catch(Exception e ){
                    result.setText("PLEASE INPUT PROPER POINTS!");
                    return;}

                if(!mtt.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }
                p=mtt;
                find_the_order(a,b,p,xp,yp,order);
                result.setText("ORDER OF POINT IS : "+order.toString());
                mtt=BigInteger.ZERO;
                yr.clearBit(0); xr.clearBit(0);

            }
            });




        corder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                result.scrollTo(0,0);
                BigInteger a = BigInteger.valueOf(0),b = BigInteger.valueOf(0),p = BigInteger.valueOf(0),mt;

                try{ a= new BigInteger(ap.getText().toString());
                    b= new BigInteger(bp.getText().toString());
                    mt= new BigInteger(pp.getText().toString());}
                catch(Exception E){
                    result.setText("PLEASE ENTER PROPER POINTS");
                    return;
                }


                if(mt.isProbablePrime(50))
                {
                    result.setText("Prime field");
//         System.out.println("prime field");
                }else{
                    result.setText("Enter proper Prime Field");
                    return;}
                System.out.println("Order function here!");
                result.setText("RESULT : ORD");
                p=mt;
                find_points(a,b,p);
                result.setText("The order of the curve is "+cnt);
                //this.jTextField8.setText(String.valueOf(cnt));
                ORD = new BigInteger(""+cnt);
                cnt=0;


            }
            });





        gen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                result.scrollTo(0,0);
                System.out.println("Generator function here!");
                result.setText("RESULT : GEN");
                BigInteger a,b,p,ord = null,max,mt;

                try{
                    a=new BigInteger(ap.getText().toString());
                    b=new BigInteger(bp.getText().toString());
                    mt=new BigInteger(pp.getText().toString());
                }

                catch(Exception e ){
                    result.setText("PLEASE ENTER PROPER POINTS");
                    return;}


                p=mt;
                if(mt.isProbablePrime(50))
                {
                    result.setText("Prime field");
//         System.out.println("prime field");
                }
                else
                {

                    result.setText("Not Prime Field!");
                    return;

                }
                p=mt;
                find_points(a,b,p);
                // res.setText("The order of the curve is "+cnt);
                //this.jTextField8.setText(String.valueOf(cnt));
                ORD = new BigInteger(""+cnt);
                cnt=0;

/*                    if(ORD.intValue() == -1)
                    {
                        res.setText("PLEASE CALCULATE CURVE ORDER BY PRESSING 'ORDER'");
                    return;
                    }*/

                ord = ORD;

                ArrayList<String> pt1=new ArrayList<String>();
                ArrayList<BigInteger> orderp=new ArrayList<BigInteger>();

                int ret=0;

                BigInteger x=BigInteger.valueOf(0),xx=BigInteger.valueOf(0),y=BigInteger.valueOf(0),temp1=BigInteger.valueOf(0),temp2=BigInteger.valueOf(0),temp3=BigInteger.valueOf(0);
                for(x=BigInteger.valueOf(0);x.compareTo(p)!=0;x=x.add(BigInteger.valueOf(1)))
                {
                    temp1=x.pow(3);
                    temp2=x.multiply(a);

                    temp3=temp2.add(b);
                    xx=temp3.add(temp1);
                    xx=xx.mod(p);
                    if(xx.compareTo(BigInteger.valueOf(0))==0)
                    {
                        y=xx;
//                   System.out.println(x+","+y);
                        pt1.add(x+","+y);
                        find_the_order(a,b,p,x,y,order);
                        orderp.add(order);
//                    System.out.println("order="+order);
                        if(order.compareTo(ord)==0)
                            cnt++;
                    }

                    ret=Legendre.symbol(xx, p);

                    if(ret==1)
                    {
                        temp1=p.add(BigInteger.valueOf(1));
                        temp2=BigInteger.valueOf(4);
                        temp1=temp1.divide(temp2);
                        y=xx.modPow(temp1, p);
//                    System.out.println(x+","+y);
                        pt1.add(x+","+y);
                        orderp.add(order);
                        cnt++;
                        y=p.subtract(y);
//                     System.out.println(x+","+y);
                        pt1.add(x+","+y);
                        find_the_order(a,b,p,x,y,order);
                        orderp.add(order);
//                      System.out.println("order="+order);
                        if(order.compareTo(ord)==0)
                            cnt++;

                    }
                }
                cnt++;
//                System.out.println("size"+pt1.size());

                for(int i1=0;i1<pt1.size();i1++)
                {
                    for(int j1=i1+1;j1<pt1.size();j1++)
                    {
                        BigInteger nm;
                        BigInteger m1=orderp.get(i1),m2=orderp.get(j1);

                        if(Integer.parseInt(m1.toString())<Integer.parseInt(m2.toString()))
                        {
                            nm=orderp.get(j1);
                            String hj;
                            hj=pt1.get(j1);

                            orderp.set(j1, orderp.get(i1));
                            pt1.set(j1, pt1.get(i1));
                            orderp.set(i1, nm);
                            pt1.set(i1, hj);

                        }

                    }
                }
                max=orderp.get(0);
                String str;
                str = "THE GENERATOR POINTS ARE:  ";
                //res.append("\n");
                for(int i=0;i<pt1.size();i++)
                {
                    if(orderp.get(i).compareTo(max)==0)
                    {
//////                    System.out.println("gen points are pt= and order="+pt1.get(i)+orderp.get(i));
//                  this.jTextArea1.append("\n\n");
                        str = str +" ("+pt1.get(i) +") ; ";
                        //str = str+"\n";
                    }
                }
                result.setText(str);


            }
            });




        fpoints.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             //   BigInteger a=new BigInteger(ap.getText().toString());
             //   BigInteger b=new BigInteger(bp.getText().toString());
             //   BigInteger p=new BigInteger(pp.getText().toString());
                result.setText("");
                result.scrollTo(0,0);
                BigInteger a = BigInteger.valueOf(0),b = BigInteger.valueOf(0),p = BigInteger.valueOf(0);
                try{
                    a= new BigInteger(ap.getText().toString());
                    b= new BigInteger(bp.getText().toString());
                    mtt= new BigInteger(pp.getText().toString());
                }
                catch(Exception e ){
                    result.setText("PLEASE INPUT PROPER POINTS!");
                    return;}
                if(!mtt.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }

                //        this.jTextField3.setText(mtt.toString());
                p=mtt;

                int ret=0;

                BigInteger x=BigInteger.valueOf(0),xx=BigInteger.valueOf(0),y=BigInteger.valueOf(0),temp1=BigInteger.valueOf(0),temp2=BigInteger.valueOf(0),temp3=BigInteger.valueOf(0),order = BigInteger.valueOf(0);
                for(x=BigInteger.valueOf(0);x.compareTo(p)!=0;x=x.add(BigInteger.valueOf(1)))
                {

                    temp1=x.pow(3);
                    temp2=x.multiply(a);

                    temp3=temp2.add(b);
                    xx=temp3.add(temp1);
                    xx=xx.mod(p);

                    if(xx.compareTo(BigInteger.valueOf(0))==0)
                    {
                        y=xx;
                        result.append(""+(pt+1)+". ("+x.toString()+","+y.toString()+")"+"  order="+give_order(x,y,a,b,mtt)+"\n");
                        //       res.append("\t");
//                    System.out.println("   "+ x +"  "+y);
                        pt++;
                    }

                    ret=Legendre.symbol(xx, p);

                    if(ret==1)
                    {
                        temp1=p.add(BigInteger.valueOf(1));
                        temp2=BigInteger.valueOf(4);
                        temp1=temp1.divide(temp2);
//
                        y=xx.modPow(temp1,p);
                        result.append(""+(pt+1)+". ("+x.toString()+","+y.toString()+")"+"  order="+give_order(x,y,a,b,mtt)+"\n");
                        //     res.append("\t");
//                    System.out.println(" "+x +" " +y);
                        pt++;
                        y=p.subtract(y);
                        result.append(""+(pt+1)+". ("+x.toString()+","+y.toString()+")"+"  order="+give_order(x,y,a,b,mtt)+"\n");
                        //            res.append("\n");
//
                        pt++;
                    }
                }
                //           pt++;

                result.append("\nNo.of points(including zero point) ="+(pt+1));
                pt=0;

            }
        });




        fy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                result.scrollTo(0,0);
                int flag1=0,flag2=0,ret;

                BigInteger n1,n2,x,ny = BigInteger.valueOf(0),np,nq,n8,n9,mn,temp1,temp2,temp3 = BigInteger.valueOf(0),xx;
                try{
                    n1= new BigInteger(ap.getText().toString());
                }
                catch(Exception e ){
//  JOptionPane.showMessageDialog(this, "first point is not good", "ERROR", JOptionPane.ERROR_MESSAGE);
                    result.setText("PLEASE ENTER PROPER POINTS!");
                    return;}

                try{
                    n2= new BigInteger(bp.getText().toString());
                }
                catch(Exception e ){
//    JOptionPane.showMessageDialog(this, "2nd point is not good", "ERROR", JOptionPane.ERROR_MESSAGE);
                    result.setText("PLEASE ENTER PROPER POINTS!");
                    return;}


                try{
                    xr= new BigInteger(pp.getText().toString());
                }
                catch(Exception e ){
//    JOptionPane.showMessageDialog(this, "3 is not good", "ERROR", JOptionPane.ERROR_MESSAGE);
                    result.setText("PLEASE ENTER PROPER POINTS!");
                    return;}

                if(!xr.isProbablePrime(50))
                {
                    result.setText("Not Prime Field!");
                    return;
                }

                try{
                    x= new BigInteger(x1.getText().toString());
                }
                catch(Exception e ){
//    JOptionPane.showMessageDialog(this, "4is not good", "ERROR", JOptionPane.ERROR_MESSAGE);
                    result.setText("PLEASE ENTER PROPER POINTS!");
                    return;}

//    int flag1=0,flag2=0,ret;

//    BigInteger ny = BigInteger.valueOf(0),np,nq,n8,n9,mn,temp1,temp2,temp3 = BigInteger.valueOf(0),xx;


                flag1=check_prime(xr);

                if(flag1==1)
                {
                    temp1=n1.pow(3);
                    temp2=temp1.multiply(BigInteger.valueOf(4));
                    temp1=n2.pow(2);
                    temp3=temp1.multiply(BigInteger.valueOf(27));
                    temp1=temp3.add(temp2);
                    temp1=temp1.mod(xr);
                    if(temp1.compareTo(BigInteger.valueOf(0))==0)
                    {
//         System.out.println("error is not detected");
                        return;
                    }
                    else
                    {

                        result.setText("\n The y coordinate(s) for the x-coordinate  " + x+" is :   ");
                        //res.append("\n");
                        temp1=x.pow(3);
                        temp2=x.multiply(n1);
                        temp3=temp2.add(n2);
                        xx=temp3.add(temp1);
                        xx=xx.mod(xr);
                        if(xx.compareTo(BigInteger.ZERO)==0)
                        {
                            ny=xx;
                            flag2=1;
//       System.out.println(" "+ny);
                            result.append("\n y  is : "+ny);
                            //res.append("\n");
//    bn=ny.toString();
                        }

                        ret=Legendre.symbol(xx, xr);
                        if(ret==1)
                        {
                            temp1=xr.add(BigInteger.valueOf(1));
                            temp2=BigInteger.valueOf(4);
                            temp1=temp1.divide(temp2);
                            ny=xx.modPow(temp1, xr);
//             System.out.println(" "+ny);
                            result.append("\n y  is : "+ny);
                            //res.append("\n");
                            ny=xr.subtract(ny);
//          System.out.println(" "+ny);
                            result.append("\n y  is : "+ny);
                            //res.append("\n");
                            flag2=1;
//            System.out.println("\n");
                            result.append("\n");
                        }
                    }

                    if(flag2==0)
                    {
//       System.out.println("There is no such x-coordinate on the given Elliptic Curve.\n");
                        result.append("NULL(no such co-ordinate).\n");
                        result.append("\n");
                    }
                    else
                    {
//       System.out.println("The field is not prime.\n");
//    this.jTextArea1.append("The field is not prime.\n");
                        result.append("\n");
                        return;
                    }
                    return;
                }
                xr=BigInteger.ZERO;
                ny.clearBit(0);
                ny=BigInteger.ZERO;


            }
            });


        clr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                pp.setText("");
                ap.setText("");
                bp.setText("");
                x1.setText("");
                y1.setText("");
                x2.setText("");
                y2.setText("");
                kp.setText("");
                result.setText("");


            }
            });




    }



    //**********************************
    //************Functons**************
    //**********************************

    public static void add_point(BigInteger point1,BigInteger point2,BigInteger n3,BigInteger nx,BigInteger ny,BigInteger np,BigInteger nq,BigInteger n8,BigInteger n9)
    {
        xr=n8;
        yr=n9;
        BigInteger temp1=BigInteger.valueOf(0),temp2=BigInteger.valueOf(0),temp3=BigInteger.valueOf(0),mn=BigInteger.valueOf(0);
//    System.out.println("n3="+n3);
        mn=nq;
        BigInteger b1=BigInteger.ZERO;
//b1=point2;
        BigInteger pm=n3.subtract(mn);
        if(nx.compareTo(BigInteger.valueOf(0))==0  && ny.compareTo(BigInteger.valueOf(0))==0)
        {

            xr=np;
            yr=nq;
            return;
        }
        if(np.compareTo(BigInteger.valueOf(0))==0  && nq.compareTo(BigInteger.valueOf(0))==0)
        {

            xr=nx;
            yr=ny;
            return;
        }

        if(ny.compareTo(pm)==0)
        {
            xr=BigInteger.valueOf(0);
            yr=BigInteger.valueOf(0);
            return;
        }
        temp1=nq.subtract(ny);
        temp2=np.subtract(nx);

        BigInteger mody,modx = BigInteger.ZERO,l;
        mody=temp1.mod(n3);
//mpz_invert(modx,temp2,p);
        for(int i=1;i<10000;i++)
        {
            b1=temp2.multiply(BigInteger.valueOf(i));
            BigInteger h=b1.mod(n3);
            if(h.compareTo(BigInteger.ONE)==0)
            {
                modx=b1.divide(temp2);
                break;
            }
        }
        temp1=modx.multiply(mody);
        l=temp1.mod(n3);
        temp1=l.pow(2);
        temp2=nx.add(np);
        temp3=temp1.subtract(temp2);
        xr=temp3.mod(n3);
        temp1=nx.subtract(xr);
        temp2=temp1.multiply(l);
        temp3=temp2.subtract(ny);
        yr=temp3.mod(n3);
//    System.out.println("xr yr add "+xr+";"+yr);

    }



    public static void find_the_order(BigInteger a,BigInteger b,BigInteger p,BigInteger xp,BigInteger yp,BigInteger v)
    {
        order=v;
        BigInteger l=BigInteger.ZERO,modx=BigInteger.ZERO,mody=BigInteger.ZERO,temp1=BigInteger.ZERO,temp2=BigInteger.ZERO,temp3=BigInteger.ZERO,xq=BigInteger.ZERO,yq=BigInteger.ZERO,c;
        xr=BigInteger.ZERO;
        yr=BigInteger.ZERO;
        xq=xp;
        yq=yp;
        c=BigInteger.valueOf(1);
        double_point(a,b,p,xq,yq,xr,yr);
        if(xr.compareTo(BigInteger.valueOf(0))==0 && (yr.compareTo(BigInteger.valueOf(0)))==0)
        {
            order=BigInteger.valueOf(2);
            return;
        }

        c=c.add(BigInteger.valueOf(1));
        xq=xr;
        yq=yr;
        while(true)
        {
            c=c.add(BigInteger.valueOf(1));
            if(xp.compareTo(xq)==0)
            {
                order=c;
                break;
            }
            else
            {
                add_point(a,b,p,xp,yp,xq,yq,xr,yr);
                xq=xr;
                yq=yr;

            }
        }
        xr.clearBit(0);
        yr.clearBit(0);
        modx.clearBit(0);
        mody.clearBit(0);
        temp1.clearBit(0);
        temp2.clearBit(0);
        temp3.clearBit(0);
        xq.clearBit(0);
        yq.clearBit(0);
        c.clearBit(0);
        l.clearBit(0);

    }



    public static void double_point(BigInteger a,BigInteger b,BigInteger p,BigInteger xp,BigInteger yp,BigInteger xr1,BigInteger yr1)

    {
        xr=xr1;
        yr=yr1;
        BigInteger modx=BigInteger.ZERO,mody = BigInteger.valueOf(0),temp1=BigInteger.ZERO,temp2=BigInteger.ZERO,temp3=BigInteger.ZERO,l=BigInteger.ZERO;
        BigInteger b1=BigInteger.ZERO;
//    System.out.println("a="+a+b+p+xp+yp+xr+yr);

        if(yp.compareTo(BigInteger.valueOf(0))==0)
        {
            xr=BigInteger.valueOf(0);
            yr=BigInteger.valueOf(0);
            return;
        }
        temp1=xp.pow(2);
        temp1=temp1.multiply(BigInteger.valueOf(3));
        temp2=temp1.add(a);
        modx=temp2.mod(p);
        temp1=yp.multiply(BigInteger.valueOf(2));
        for(int i=1;i<10000;i++)
        {

            b1=temp1.multiply(BigInteger.valueOf(i));
            BigInteger h=b1.mod(p);
            if(h.compareTo(BigInteger.ONE)==0)
            {
                mody=b1.divide(temp1);
                break;
            }
        }
        temp3=modx.multiply(mody);
        l=temp3.mod(p);
        temp1=l.pow(2);
        temp2=xp.multiply(BigInteger.valueOf(2));
        temp3=temp1.subtract(temp2);
        xr=temp3.mod(p);
        temp1=xp.subtract(xr);
        temp2=temp1.multiply(l);
        temp3=temp2.multiply(yp);
        temp3=temp2.subtract(yp);
        yr=temp3.mod(p);

    }



    static void find_points(BigInteger a,BigInteger b,BigInteger p)
    {
        //     Legendre lk=new Legendre();
        int ret=0;
//       System.out.println("a="+a+"p="+p);
        BigInteger x=BigInteger.valueOf(0),xx=BigInteger.valueOf(0),y=BigInteger.valueOf(0),temp1=BigInteger.valueOf(0),temp2=BigInteger.valueOf(0),temp3=BigInteger.valueOf(0),order = BigInteger.valueOf(0);
        for(x=BigInteger.valueOf(0);x.compareTo(p)!=0;x=x.add(BigInteger.valueOf(1)))
        {

            temp1=x.pow(3);
            temp2=x.multiply(a);

            temp3=temp2.add(b);
            xx=temp3.add(temp1);
            xx=xx.mod(p);

            if(xx.compareTo(BigInteger.valueOf(0))==0)
            {
                y=xx;
                System.out.println("  "+ x +"  "+y);
                cnt++;
            }

            ret=Legendre.symbol(xx, p);

            if(ret==1)
            {
                temp1=p.add(BigInteger.valueOf(1));
                temp2=BigInteger.valueOf(4);
                temp1=temp1.divide(temp2);
                y=xx.modPow(temp1, p);
                cnt++;
                y=p.subtract(y);
                System.out.println("  "+x+  "  "+y);


                cnt++;
            }
        }
        cnt++;




    }



    static String give_order(BigInteger x,BigInteger y,BigInteger a,BigInteger b,BigInteger mtt)
    {

        //order=v;
        BigInteger l=BigInteger.ZERO,modx=BigInteger.ZERO,mody=BigInteger.ZERO,temp1=BigInteger.ZERO,temp2=BigInteger.ZERO,temp3=BigInteger.ZERO,xq=BigInteger.ZERO,yq=BigInteger.ZERO,c;
        xr=BigInteger.ZERO;
        yr=BigInteger.ZERO;
        xq=x;
        yq=y;
        BigInteger p=mtt;
        c=BigInteger.valueOf(1);
        double_point(a,b,p,xq,yq,xr,yr);
        if(xr.compareTo(BigInteger.valueOf(0))==0 && (yr.compareTo(BigInteger.valueOf(0)))==0)
        {
            return "2";
        }

        c=c.add(BigInteger.valueOf(1));
        xq=xr;
        yq=yr;
        while(true)
        {
            c=c.add(BigInteger.valueOf(1));
            if(x.compareTo(xq)==0)
            {
                order=c;
                break;
            }
            else
            {
                add_point(a,b,p,x,y,xq,yq,xr,yr);
                xq=xr;
                yq=yr;

            }
        }
        xr.clearBit(0);
        yr.clearBit(0);
        modx.clearBit(0);
        mody.clearBit(0);
        temp1.clearBit(0);
        temp2.clearBit(0);
        temp3.clearBit(0);
        xq.clearBit(0);
        yq.clearBit(0);
        c.clearBit(0);
        l.clearBit(0);


        return order.toString();


      /*  BigInteger xq=BigInteger.ZERO,yq=BigInteger.ZERO,c;
        BigInteger p,xp,yp,order = BigInteger.ZERO;
        try{
            xp=x;
            yp=y;
        }
        catch(Exception e ){

            return new String("");}

        if(!mtt.isProbablePrime(50))
        {

            return new String("");
        }
        p=mtt;

        xq=xp;
        yq=yp;
        c=BigInteger.valueOf(1);
        double_point(a,b,p,xq,yq,xr,yr);
//    System.out.println("xr="+xr+"yr="+yr);
        if(xr.compareTo((BigInteger.valueOf(0)))==0  && yr.compareTo((BigInteger.valueOf(0)))==0)
        {
            order=BigInteger.valueOf(2);

        }

        c=c.add(BigInteger.valueOf(1));
        xq=xr;
        yq=yr;
//    System.out.println("c="+c);
//     System.out.println("xp="+xr+"xq="+yr+"order="+order);
        do
        {
            c=c.add(BigInteger.valueOf(1));

            if(xp.compareTo(xq)==0)
            {

                order=c;

                break;

            }
            else
            {
                add_point(a,b,p,xp,yp,xq,yq,xr,yr);
                xq=xr;
                yq=yr;
//        System.out.println("xr= yr="+xr+yr);

            }
        }while(true);


        mtt=BigInteger.ZERO;
        yr.clearBit(0); xr.clearBit(0);
        return order.toString();*/

    };



    public static int check_prime(BigInteger m1 )
    {

        BigInteger mn=BigInteger.valueOf(0);
        BigInteger h=BigInteger.valueOf(0);
        if(xr.isProbablePrime(50)==false)
            xr.nextProbablePrime();
        do{
            mn=xr.mod(BigInteger.valueOf(4));
            if(mn.compareTo(BigInteger.valueOf(3))==0)
                return 1;
            else
                xr=xr.nextProbablePrime();
        }while(true);

    }



    public static BigInteger[] addition(BigInteger x1, BigInteger y1, BigInteger x2, BigInteger y2, BigInteger mod, BigInteger a)
    {
        BigInteger pt[];
        BigInteger n,d,l;
        pt = new BigInteger[2];


        if( y1.negate().compareTo(y2) == 0)
        {
            pt[0] = pt[1] = BigInteger.ONE.negate();
        }
        else if( y1.compareTo(y2) == 0 && x1.compareTo(x2) == 0)
        {
            if( y1.compareTo(BigInteger.ZERO) == 0 )
            {
                pt[0] = pt[1] = BigInteger.ONE.negate();
            }
            else
            {
                n = x1.multiply(x1).multiply(new BigInteger("3")).add(a);
                d = y1.multiply(new BigInteger("2"));

                l = n.mod(mod).multiply(d.modInverse(mod));
                // l = l.mod(mod);

                //             System.out.println("\n L1 = "+l.toString());


                pt[0] = l.multiply(l).subtract(x1.multiply(new BigInteger("2")));

                pt[1] = l.multiply(x1.subtract(pt[0])).subtract(y1);

                pt[0] = pt[0].mod(mod);
                pt[1] = pt[1].mod(mod);
            }
        }
        else
        {
            n = y2.subtract(y1).mod(mod);
            d = x2.subtract(x1);

            if(d.compareTo(BigInteger.ZERO) == 0)
            {
                pt[0] = pt[1] = BigInteger.ONE.negate();
            }
            else
            {
                d = d.modInverse(mod);
                l = n.multiply(d);

                //      System.out.println("\n N = "+ n.toString()+" D = "+d.toString()+" L2 = "+l.toString());

                pt[0] = l.multiply(l).subtract(x1).subtract(x2).mod(mod);
                pt[1] = l.multiply(x1.subtract(pt[0])).subtract(y1).mod(mod);
            }
        }
        return pt;
    }



    private void setupUIViews()
    {
        add = (Button)findViewById(R.id.btnadd);
        sub = (Button)findViewById(R.id.btnsub);
        mul = (Button)findViewById(R.id.btnmul);
        dub = (Button)findViewById(R.id.btndub);
        porder = (Button)findViewById(R.id.btnporder);
        corder = (Button)findViewById(R.id.btncorder);
        gen = (Button)findViewById(R.id.btngen);
        fpoints = (Button)findViewById(R.id.btnfpoints);
        fy = (Button)findViewById(R.id.btnfy);
        clr = (Button)findViewById(R.id.btnclr);

        ap = (EditText)findViewById(R.id.ina);
        bp = (EditText)findViewById(R.id.inb);
        pp = (EditText)findViewById(R.id.inp);
        kp = (EditText)findViewById(R.id.ink);
        x1 = (EditText)findViewById(R.id.inx1);
        y1 = (EditText)findViewById(R.id.iny1);
        x2 = (EditText)findViewById(R.id.inx2);
        y2 = (EditText)findViewById(R.id.iny2);

        result = (TextView)findViewById(R.id.result);

        result.setMovementMethod(new ScrollingMovementMethod());

    }
}
