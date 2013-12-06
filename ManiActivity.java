package com.example.sofatsms2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Contacts extends Activity {
	String number = "", name="";
	String names;
	ListView j;
	EditText ed;
CheckBox kl;
TextView hj;
int lengthr;
	private List<Contactss> mycont=new ArrayList<Contactss>();
	ArrayAdapter<Contactss> adapter;	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	setContentView(R.layout.conn);
	ed=(EditText) findViewById(R.id.ser);
	mycontact();
allcontact();
registerClickCallback();
search();
	}
public void search(){

j.setTextFilterEnabled(true);
ed.addTextChangedListener(new TextWatcher(){
TextView g=(TextView) findViewById(R.id.hjj);	


	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
adapter.getFilter().filter(cs.toString());
g.setText(cs);
	}
});
}
	private void mycontact() {
		// TODO Auto-generated method stub
		ContentResolver cr=getContentResolver();
		Cursor cur=cr.query(ContactsContract.Contacts.CONTENT_URI, null,null,null,"UPPER(" + ContactsContract.Contacts.DISPLAY_NAME + ") ASC");

	if(cur.getCount()>0){

		while(cur.moveToNext()){
			String id=cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
			 name=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

			 if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0){
				System.out.println("name:" + name +",ID:" + id);
				Cursor pcur=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ "=?",new String[]{id},null);
			while(pcur.moveToNext()){
				number=pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				mycont.add(new Contactss(name,number,R.drawable.l));				
		
			}
			pcur.close();
			}
		}
	}

	}
	private void allcontact() {
		// TODO Auto-generated method stub

		adapter=new MyListAdapter();
j=(ListView) findViewById(R.id.contacts);
j.setAdapter(adapter);
j.setTextFilterEnabled(true);
		
	}
	public void registerClickCallback(){
		ListView list=(ListView) findViewById(R.id.contacts);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
					long id) {
				// TODO Auto-generated method stub
		Contactss mycon=mycont.get(position);
		
		Bundle rt=getIntent().getExtras();
		String message="You clicked on "+position+"which make"+mycon.getname()+"user name is"+mycon.getnumber();
				TextView jk=(TextView) findViewById(R.id.mess);
	
				Intent gh=new Intent(Contacts.this, listmess.class);
				gh.putExtra("Name", rt.getString("name"));
				gh.putExtra("contact", mycon.getnumber());
				startActivity(gh);

			}
		
		
		});
	}

private class MyListAdapter extends ArrayAdapter<Contactss>{
 public	MyListAdapter(){
		super(Contacts.this,R.layout.list,mycont);
		 
	}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
View itemview=convertView;
if(itemview == null){
	itemview=getLayoutInflater().inflate(R.layout.list,parent,false);
}
Contactss d=mycont.get(position);
TextView th=(TextView)itemview.findViewById(R.id.mess);
th.setText(d.getname());
ImageView im=(ImageView)itemview.findViewById(R.id.img);
im.setImageResource(d.getimage());
TextView thh=(TextView)itemview.findViewById(R.id.phonen);
thh.setText(d.getnumber());
//kl=(CheckBox)itemview.findViewById(R.id.select);
//kl.setText(d.getnumber());
//kl.setOnCheckedChangeListener(myl);
return itemview;
}
//OnCheckedChangeListener myl=new OnCheckedChangeListener(){

	//@Override
	//public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		//if(isChecked){
//hj=(TextView) findViewById(R.id.hjj);
//hj.setText(kl.getText().toString());
	//	}
//	}

//};
}
}

  















