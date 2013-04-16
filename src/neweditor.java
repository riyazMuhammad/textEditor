
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
@SuppressWarnings("serial")
public class neweditor extends Frame implements ActionListener{
	String bg,fg,fs;    //file props(for open purpose)
	TextArea t1;
	String copstring="";
	MenuItem pas;
	int fsize=14;
	@SuppressWarnings("deprecation")
	public neweditor(String title) {
		super(title);
		setSize(500, 500);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setVisible(true);
		t1=new TextArea();
		add(t1);
		setFont(new Font("TimesRoman",Font.PLAIN ,14));
		MenuBar mybar=new MenuBar();
		setMenuBar(mybar);	
		//file menu
		Menu file = new Menu("File");
		MenuItem sav=new MenuItem("save", new MenuShortcut(KeyEvent.VK_S));
		MenuItem ne = new MenuItem("new");
		MenuItem op=new MenuItem("open");
		MenuItem ex =new MenuItem("exit");
		file.add(ne);
		file.add(op);
		file.add(sav);
		file.add(ex);
		//edit menu
		Menu edit = new Menu("Edit");
		MenuItem cop=new MenuItem("copy");
		edit.add(cop);
		pas=new MenuItem("paste");
		pas.disable();
		edit.add(pas);
		MenuItem cu=new MenuItem("cut");
		edit.add(cu);
		MenuItem fo= new MenuItem("font size");
		edit.add(fo);
		//Color menu
		Menu col= new Menu("Color");
		MenuItem font= new MenuItem("font");
		MenuItem bg= new MenuItem("background");
		col.add(font);
		col.add(bg);
		//font style menu
		Menu fs= new Menu("Style");
		MenuItem bo= new MenuItem("bold");
		MenuItem it= new MenuItem("italic");
		MenuItem no= new MenuItem("normal");
		fs.add(bo);
		fs.add(it);
		fs.add(no);
		//about menu
		Menu hlp=new Menu("Help");
		MenuItem abt=new MenuItem("about");
		hlp.add(abt);
		mybar.add(file);
		mybar.add(edit);
		mybar.add(col);
		mybar.add(fs);
		mybar.add(hlp);
		
		ne.addActionListener(this);
		sav.addActionListener(this);
		ex.addActionListener(this);
		cop.addActionListener(this);
		pas.addActionListener(this);
		cu.addActionListener(this);
		fo.addActionListener(this);
		abt.addActionListener(this);
		op.addActionListener(this);
		font.addActionListener(this);
		bg.addActionListener(this);
		bo.addActionListener(this);		
		no.addActionListener(this);	
		it.addActionListener(this);	
	}
	public static void main(String[] args) {
		
		new neweditor("MyEditor <<<RIYAZ>>>");
						
	}

	
	@SuppressWarnings({ "deprecation", "static-access" })
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("about"))
		{
			
			t1.setText("Hi my name is Riyaz.\n" +"\n"+
			"\n I've created this Simple TEXT EDITOR With pure Interest In java.\n" +
			"contact me at riyazrmk4@gmail.com or riyaz@riyazm.com \n" +
			"in case you need any help regarding my code.\n");
			t1.setEditable(false);
		}
		
		if(e.getActionCommand().equals("new"))
		{
			t1.setText("");
			t1.setEditable(true);
			t1.setBackground(null);
			t1.setForeground(null);
			t1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
		}
		if(e.getActionCommand().equals("save"))
		{
			try {
				savefiledia();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("exit")){
			System.exit(0);
		}
		if(e.getActionCommand().equals("copy"))
		{  
			pas.enable();
			copstring=t1.getSelectedText();
			
		}
		if(e.getActionCommand().equals("paste"))
		{ 
			t1.insert(copstring, t1.getSelectionStart()); //getSelectionEnd() also works fine only here
		   pas.disable();
		}
		if(e.getActionCommand().equals("cut"))
		{
			pas.enable();
			copstring=t1.getSelectedText();

			 t1.replaceText("", t1.getSelectionStart(), t1.getSelectionEnd());
			
		}
		if(e.getActionCommand().equals("font size")){
			new fontslider();
		}
		if(e.getActionCommand().equals("font")){
			
			t1.setForeground(new JColorChooser().showDialog(null,"choose a color",Color.white));
		}
		if(e.getActionCommand().equals("background")){
			
			t1.setBackground(new JColorChooser().showDialog(null,"choose a color",Color.white));
		}
		if(e.getActionCommand().equals("bold"))
		{
			t1.setFont(new Font("TimesRoman",Font.BOLD ,fsize));
		}
		if(e.getActionCommand().equals("normal"))
		{
			t1.setFont(new Font("TimesRoman",Font.PLAIN ,fsize));
		}
		if(e.getActionCommand().equals("italic"))
		{
			t1.setFont(new Font("TimesRoman",Font.ITALIC ,fsize));
		}
		
		if(e.getActionCommand().equals("open"))
		{   
			t1.setText("");
			t1.setBackground(null);
			t1.setForeground(null);
			t1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			String path;
			FileDialog fd=new FileDialog(new Frame(),"select a text file open",FileDialog.LOAD);
			fd.setVisible(true);
			path=fd.getDirectory()+"/"+fd.getFile();
			Scanner s1 = null;
			try {
				s1 = new Scanner(new File(path));
			} catch (FileNotFoundException e1) {
				final Dialog d1 = new Dialog(new Frame());
				d1.setVisible(true);
				d1.setSize(300, 200);
				d1.add(new Label("File not found!!!!!!!!") );
				d1.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					d1.dispose();
				}
				});
			}
			
			while(s1.hasNext())
			{  		bg=s1.nextLine();
					fg=s1.nextLine();
					fs=s1.nextLine();
									
			t1.insert(s1.nextLine(), t1.getSelectionStart());
			t1.insert("\n", t1.getSelectionStart());
			//t1.insertText(s1.next(),t1.getSelectionStart());   //0 in place of -1 is making the text to appear in reverse order ;) 
			//where as 1 jumbling the words or -1 is same as t1.getselectionstart
			
			}
			
			myscanner();
		}
	}
		
		
	
	private void myscanner(){
		Scanner s;
		//example
		//java.awt.Font[family=Serif,name=TimesRoman,style=bold,size=14]	
	fs= fs.replace(","," ");
	fs= fs.replace("]", "");
	String fs1= fs;
	s = new Scanner(fs1);
	s.findInLine("size=");
	fsize=s.nextInt();
	s = new Scanner(fs);
	s.findInLine("style=");
		fs=s.next();
	
	if(fs.equals("italic")){
		t1.setFont(new Font("TimesRoman", Font.ITALIC, fsize));
	}
	else if(fs.equals("bold")){
		t1.setFont(new Font("TimesRoman", Font.BOLD, fsize));
	}
	else{
		t1.setFont(new Font("TimesRoman", Font.PLAIN, fsize));
	}
	
	
	
	int bgar[]= new int[3];
	int fgar[]= new int[3];
	
		//example
		//java.awt.Color[r=153,g=255,b=153]
		int i=0;
		fg=fg.replace(","," ");
		fg=fg.replace("java.awt.Color[r=","");
		fg=fg.replace("g=","");
		fg=fg.replace("b=","");
		fg=fg.replace("]","");
				
		s = new Scanner(fg);
			while(s.hasNext()){
			if(s.hasNextInt()){
				fgar[i]=s.nextInt();
				i++;
			}
			else
			{
				fg=s.next();
			}
		}   
			
		//example
		//   java.awt.Color[r=153,g=0,b=255]
		i=0;
		bg=bg.replace(","," ");
		bg=bg.replace("java.awt.Color[r=","");
		bg=bg.replace("g=","");
		bg=bg.replace("b=","");
		bg=bg.replace("]","");
				
		s = new Scanner(bg);
			while(s.hasNext()){
			if(s.hasNextInt()){
				bgar[i]=s.nextInt();
				i++;
			}
			else
			{
				bg=s.next();
			}
		}   
		
		t1.setBackground(new Color(bgar[0], bgar[1], bgar[2]));
		t1.setForeground(new Color(fgar[0], fgar[1], fgar[2]));
		
	}
	
	
		private void savefiledia() throws IOException {
		String dir,fil,path;
		FileWriter f1;
		FileDialog fd=new FileDialog(new Frame(), "Save destination", FileDialog.SAVE);
		fd.setVisible(true);
		dir=fd.getDirectory();
		fil=fd.getFile();
		path=dir+"/"+fil;
		f1=new FileWriter(path, true);
		f1.write(t1.getBackground()+"\n"+t1.getForeground()+"\n"+t1.getFont()+"\n");
		f1.write(t1.getText());
		f1.close();
		
	}
		class fontslider extends JFrame implements ChangeListener{
			JSlider js;
			JPanel sp,fp;
			JLabel fval,fmsg;
			
			public fontslider() {
				super("font slider");
				setVisible(true);
				setLayout(new GridLayout(1, 2));
				setSize(420, 150);
				addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					dispose();
				}
				});
				js = new JSlider(JSlider.HORIZONTAL,0, 40, 14);
				js.setMajorTickSpacing(10);
				js.setMinorTickSpacing(1);
				js.setPaintTicks(true);
				js.setPaintLabels(true);
				js.addChangeListener(this);
				fval  = new JLabel("14");
				sp= new JPanel(new GridLayout(2, 1));
				sp.add(js);
				sp.add(fval);
				add(sp);
				fmsg= new JLabel("RiyaS");
				fp = new JPanel();
				fp.add(fmsg);
				add(fp);
			}
			public void stateChanged(ChangeEvent arg0) {
				
				fsize= js.getValue();
				fval.setText(""+fsize);
				fmsg.setFont(new Font("TimesRoman", Font.PLAIN, fsize));
				if(t1.getFont().toString().contains("bold")){
					t1.setFont(new Font("TimesRoman", Font.BOLD, fsize));
				}
				else if(t1.getFont().toString().contains("italic")){
					t1.setFont(new Font("TimesRoman", Font.ITALIC, fsize));
				}
				else{
					t1.setFont(new Font("TimesRoman", Font.PLAIN, fsize));
				}
			}
		}
		
	
}

