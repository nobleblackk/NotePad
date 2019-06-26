import java.io.*;
import java.awt.*;
import java.awt.event.*;
class NotePad1 implements ActionListener,ItemListener,TextListener
{
	private Panel p2;
	private Font ft;
	private Frame f;
	private String s;
	private File fs;
	private String a,b; 
	private MenuBar mb;
	private Menu file,edit,others,options;
	private MenuItem neu,open,save,saveas,cut,copy,paste,one,two;
	private CheckboxMenuItem toolbar,statusbar;
	private TextArea ta;
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;	
	NotePad1()
	{
		p2=new Panel(new GridLayout(1,5));
		p2.setBackground(Color.LIGHT_GRAY);
		f=new Frame("NotePad");
		f.setBounds(450,250,1000,750);
		
		mb=new MenuBar();
		
		file =new Menu("File");
		edit=new Menu("Edit");
		options=new Menu("Options");
		others=new Menu("Options");
		
		l1=new Label("Characters :");
		l2=new Label("Words :");
		l3=new Label("Alphabets :");
		l4=new Label("Vowels :");
		l5=new Label("Lines :");
		l6=new Label("0");
		l7=new Label("0");	
		l8=new Label("0");	
		l9=new Label("0");
		l10=new Label("0");
		
		p2.add(l1);
		p2.add(l6);
		p2.add(l2);
		p2.add(l7);
		p2.add(l3);
		p2.add(l8);
		p2.add(l4);
		p2.add(l9);
		p2.add(l5);
		p2.add(l10);
				
		neu=new MenuItem("New");
		open=new MenuItem("Open");
		save=new MenuItem("Save");
		saveas=new MenuItem("Save As");
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		paste=new MenuItem("Paste");
		one=new MenuItem("One");
		two=new MenuItem("Two");
		
		ta=new TextArea();
		ft=new Font("Arial",Font.PLAIN,30);
		ta.setFont(ft);
		
		ta.addTextListener(this);
		neu.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		
		toolbar=new CheckboxMenuItem("Toolbar");
		statusbar=new CheckboxMenuItem("Statusbar");
		
		toolbar.addItemListener(this);
		statusbar.addItemListener(this);		
		
		file.add(neu);
		file.add(open);
		file.addSeparator();
		file.add(save);
		file.add(saveas);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);	
		
		others.add(one);	
		others.add(two);
				
		options.add(toolbar);
		options.add(statusbar);
		options.add(new MenuItem("-"));
		options.add(others);
		
		mb.add(file);
		mb.add(edit);
		mb.add(options);
		
		f.add(p2,"South");
		f.add(ta);	
		f.setMenuBar(mb);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object o;
		o=e.getSource();
		if(o==neu)
		{
			ta.setText(" ");///ta.setText(""); 
		}
		else if(o==open)
		{	
			ta.setText("");
			FileDialog fd;
			fd=new FileDialog(f,"Open",FileDialog.LOAD);
			fd.setVisible(true);	
			//String a,b;
			a=fd.getFile();
			b=fd.getDirectory();
			if(a!=null&&a.length()!=0)
			{	
				 fs=new File(b,a);
				//FileReader fr =new FileReader(fg);	
				FileInputStream fis;
				try
				{
					int c;
					fis=new FileInputStream(fs);
					while((c=fis.read())!=-1)
					{
						ta.append(""+(char)c);
					}
				}
				catch(IOException io)
				{
					ta.setText(io.getMessage());
				}
			}
		}
		else if(o==save)
		{
			if(a==null||a.length()==0)///if(fs.exists()==false)
			{
				//System.out.println("a");
				FileDialog fd;
				fd=new FileDialog(f,"Save",FileDialog.SAVE);
				fd.setVisible(true);
				a=fd.getFile();
				b=fd.getDirectory();
				fs=new File(b,a);
				FileOutputStream fos;
				try
				{	
					fos=new FileOutputStream(fs);
					char b[];
					b=ta.getText().toCharArray();
					for(char x:b)
					{
						fos.write((int)x);
					}
				}
				catch(IOException io)
				{
					ta.append("\n"+io.getMessage());
				}
			}
			else
			{
				FileOutputStream fos;
				try
				{	
					fos=new FileOutputStream(fs);
					char b[];
					b=ta.getText().toCharArray();
					for(char x:b)
					{
						fos.write((int)x);
					}
				}
				catch(IOException io)
				{
					ta.append("\n"+io.getMessage());
				}	
			}
		}
		else if(o==saveas)
		{
			FileDialog fd;
			fd=new FileDialog(f,"Save",FileDialog.SAVE);
			fd.setVisible(true);
			a=fd.getFile();
			b=fd.getDirectory();
			fs=new File(b,a);
			FileOutputStream fos;
			try
			{	
				fos=new FileOutputStream(fs);
				char b[];
				b=ta.getText().toCharArray();
				for(char x:b)
				{
					fos.write((int)x);
				}
			}
			catch(IOException io)
			{
				ta.append("\n"+io.getMessage());
			}
		}
		else if(o==cut)
		{
			s=ta.getSelectedText();
			ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
		}
		else if(o==copy)
		{
			s=ta.getSelectedText();
		}
		else if(o==paste)
		{
			if(ta.getSelectedText()==null||ta.getSelectedText().length()==0)
			                	ta.insert(s, ta.getSelectionStart());
			else
			{
			ta.replaceRange("",ta.getSelectionStart(),ta.getSelectionEnd());
			ta.insert(s,ta.getSelectionStart());
			}	
				
		}					
	}
	public void itemStateChanged(ItemEvent e)
	{	
	}
	public void textValueChanged(TextEvent e)
	{
		Object o;
		o=e.getSource();
		if(o==ta)
		{
			String s=ta.getText();
			if(s!=null&&s.length()!=0)
			{
				int i=s.length();
				l6.setText(""+i);		
				int count=1;		
				for(int j=0;j<s.length();j++)
				{	
					if(' '==s.charAt(j))
						count++;
					else if('\t'==s.charAt(j))
						count++;
					else if('\n'==s.charAt(j))
						count++;
				}
				l7.setText(""+count);
				int count1=0;
				for(int j=0;j<s.length();j++)
				{
					if(s.charAt(j)>='A'&&s.charAt(j)<='Z')
						count1++;
					else if(s.charAt(j)>='a'&&s.charAt(j)<='z')
						count1++;
				}
				l8.setText(""+count1);
				int count2=0;
				for(int j=0;j<s.length();j++)
				{
					switch (s.charAt(j))
					{
						case 'a':	
						case 'e':
						case 'i':
						case 'o':
						case 'u':
						case 'A':	
						case 'E':
						case 'I':
						case 'O':
						case 'U':
							count2++;
							break;
			
					}	
				}
				l9.setText(""+count2);
				int count3=1;
				for(int j=0;j<s.length();j++)
				{
					if(s.charAt(j)=='\n')
						count3++;
				}
				l10.setText(""+count3);
			}
			else
			{
				l6.setText("0");
				l7.setText("0");
				l8.setText("0");
				l9.setText("0");
				l10.setText("0");
			}
		}			
	
	}
	public static void main(String...abhishek)
	{
		new NotePad1();
	}
}	
		