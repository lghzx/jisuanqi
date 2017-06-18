package com.example.zx;



import com.example.zx.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MainActivity extends Activity {

	private TextView view;
	private RadioGroup group;
	private RadioButton radioButton;
	private int ac=3;
	private EditText editText[][]=new EditText[3][4];
	private int h_niao[]={0,0,0,0};
	private EditText edit;
	private double price=0.5;
	private int t_niao[]={0,0,0,0};
	private int h_xi[]={0,0,0,0};
	double result[]={0,0,0,0};
	private TextView result_view[]=new TextView[4];
	private Button calculate_btn,clean_btn,exit_btn;
	private MyCalculateOnClickListener myCal=new MyCalculateOnClickListener();
	private MyOnFocusChangListener myOnFocusChangListener=new MyOnFocusChangListener();
	private MyClearOnClickListener myClearOnClickListener=new MyClearOnClickListener();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*findObjeck();*/
		calculate_btn=(Button)findViewById(R.id.buttonJiSuan);
		exit_btn=(Button)findViewById(R.id.buttonexit);
		clean_btn=(Button)findViewById(R.id.buttonclean);
		result_view[0]=(TextView)findViewById(R.id.textView1);
		result_view[1]=(TextView)findViewById(R.id.textview2);
		result_view[2]=(TextView)findViewById(R.id.textview3);
		result_view[3]=(TextView)findViewById(R.id.textview4);
		editText[0][0]=(EditText)findViewById(R.id.edit1);
		editText[0][1]=(EditText)findViewById(R.id.edit2);
		editText[0][2]=(EditText)findViewById(R.id.edit3);
		editText[0][3]=(EditText)findViewById(R.id.edit4);
		editText[1][0]=(EditText)findViewById(R.id.edit5);
		editText[1][1]=(EditText)findViewById(R.id.edit6);
		editText[1][2]=(EditText)findViewById(R.id.edit7);
		editText[1][3]=(EditText)findViewById(R.id.edit8);
		editText[2][0]=(EditText)findViewById(R.id.edit9);
		editText[2][1]=(EditText)findViewById(R.id.edit10);
		editText[2][2]=(EditText)findViewById(R.id.edit11);
		editText[2][3]=(EditText)findViewById(R.id.edit12);
		group=(RadioGroup)findViewById(R.id.group1);
		radioButton=(RadioButton)findViewById(R.id.radio1);
		radioButton=(RadioButton)findViewById(R.id.radio0);
		edit=(EditText)findViewById(R.id.editprice);
		view=(TextView)findViewById(R.id.viewdin);
		group.setOnCheckedChangeListener(new MyOncheckChangListener());
		
		calculate_btn.setOnClickListener(myCal);
		setEditFocusListener();
		
		exit_btn.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		clean_btn.setOnClickListener(myClearOnClickListener);
	}

	

	/*private void findObjeck() {
		// TODO Auto-generated method stub
		
	}*/
	private void init()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(editText[i][j].getText().toString().equals(""))
					editText[i][j].setText("0");
			}
		}
		if(edit.getText().toString().equals(""))
			edit.setText("0.5");
		try{for(int i=0;i<ac; i++){
			
				h_niao[i]=Integer.parseInt(editText[0][i].getText().toString());
				t_niao[i]=Integer.parseInt(editText[1][i].getText().toString());
				h_xi[i]=Integer.parseInt(editText[2][i].getText().toString());
			}
		
		price=Double.parseDouble(edit.getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View v) {
				
            }
	private void calculate()
	{
		result[0]=result[1]=result[2]=result[3]=0;
		for(int i=0;i<ac;i++)
		{
			for(int j=0; j<ac;j++)
			{
				result[i]+=compare(h_xi[i],h_xi[j])*(t_niao[i]+t_niao[j]);
				result[i]+=(myInt(h_xi[i])-myInt(h_xi[j]))*(h_niao[i]+1)*(h_niao[j]+1)*price;
				
			}
		}
	}
	private void view()
	{
		for(int i=0;i<ac;i++){
			result_view[i].setText(String.valueOf(Math.round(result[i]*10/10.0)));
			
		}
	}
	class MyOncheckChangListener implements RadioGroup.OnCheckedChangeListener{
		
		@Override
		public void onCheckedChanged(RadioGroup group,int checkedId)
		{
			clear();
			boolean tag;
			if(radioButton.getId()==checkedId){
			ac=3;
			tag=false;
		}else
			{
				ac=4;
				tag=true;
			}
			for (int i=0;i<3;i++)
			{
				editText[i][3].setEnabled(tag);
			}
			result_view[3].setEnabled(tag);
			view.setEnabled(tag);
		}
	}
	 class MyClearOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			clear();
		}

	}
	 private void clear()
	 {
		 for(int i =0;i<3;i++)
		 {
			 for(int j=0;j<4;j++){
				 editText[i][j].setText("0");
			 }
		 }
		 for (int j=0;j<4;j++)
		 {
			 result_view[j].setText("0");
		 }
	 }
	 private void setEditFocusListener()
	 {
		 for(int i=0;i<3;i++)
		 {
			 for(int j=0;j<4;j++)
			 {
				 editText[i][j].setOnFocusChangeListener(myOnFocusChangListener);
			 }
		 }
		 edit.setOnFocusChangeListener(myOnFocusChangListener);
	 }
	 class MyCalculateOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			init();
			calculate();
			view();
		}
		 
	}
	 private int compare(int x ,int y){
		if(x==y)return 0;
		 return x>y?1:-1;
		}
	 private int myInt(int h_xi)
	 {
		 int tag=1,h_xi_abs;
		 h_xi_abs=Math.abs(h_xi);
		 if(h_xi<0)tag=-1;
		 if(h_xi_abs%10>=5)
			 h_xi_abs=(h_xi_abs/10+1)*10;
		 else
			 h_xi_abs=h_xi_abs/10*10;
		 return h_xi_abs*tag;
	 }
	  class MyOnFocusChangListener implements View.OnFocusChangeListener{

		  
		@Override
		public void onFocusChange(View v, boolean hanFocus) {
			// TODO Auto-generated method stub
			EditText editText=(EditText)v;
			if(hanFocus)
			{
				editText.setText("");
			}
			else {
				if(editText.getText().toString().equals(""))
				{
					if(editText.getId()==R.id.editprice)
						editText.setText("0.5");
					else
						editText.setText("0");
					
				}
			}
			
		}
		  
	  }

}
