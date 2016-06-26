//////////////////////////////////////////// 
// Project skeleton for Computer 3D Graphics
// MVC-based design
// Author: Chus Martín
// 2014
//////////////////////////////////////////// 

package view;

//JOGL imports
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JButton;

//Swing imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;



//AWT imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//Specific imports
import controller.Controller;

public class SwingGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller controller;
	private GLCanvas canvas; //AWT-based canvas!!!!


	/////////////////////////////////
	private SwingGUI(){

		// Set the window
		super("JOGL-project for 3D Graphics");
		this.getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000, 600));
		this.setLocation(400, 300);
		//log_JFrame();


		// Build and add the canvas
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		log_OpenGL(caps);

		canvas = new GLCanvas(caps);
		int canvasWidth= 500;
		int canvasHeight= 250;		
		canvas.setSize(canvasWidth, canvasHeight);
		this.getContentPane().add(canvas,BorderLayout.CENTER);
		log_Canvas();

		JPanel panelX = new JPanel();
		panelX.setBorder(new TitledBorder("Rotar X"));

		JButton buttonRotarXIzda = new JButton ("Izquierda");
		buttonRotarXIzda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean girarDerecha = false;
				controller.rotateX(girarDerecha);
			}
		});


		JButton buttonRotarXDerech = new JButton ("Derecha");
		buttonRotarXDerech.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean girarDerecha = true;
				controller.rotateX(girarDerecha);
			}
		});

		panelX.add(buttonRotarXIzda);
		panelX.add(buttonRotarXDerech);



		JPanel panelY = new JPanel();
		panelY.setBorder(new TitledBorder("Rotar Y"));

		JButton buttonRotarYIzda = new JButton ("Izquierda");
		buttonRotarYIzda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean girarDerecha = false;
				controller.rotateY(girarDerecha);
			}
		});


		JButton buttonRotarYDerech = new JButton ("Derecha");
		buttonRotarYDerech.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean girarDerecha = true;
				controller.rotateY(girarDerecha);
			}
		});


		panelY.add(buttonRotarYIzda);
		panelY.add(buttonRotarYDerech);



		JPanel panelZ = new JPanel();
		panelZ.setBorder(new TitledBorder("Rotar Z"));

		JButton buttonRotarZIzda = new JButton ("Izquierda");
		buttonRotarZIzda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean girarDerecha = false;
				controller.rotateZ(girarDerecha);
			}
		});


		JButton buttonRotarZDerech = new JButton ("Derecha");
		buttonRotarZDerech.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean girarDerecha = true;
				controller.rotateZ(girarDerecha);
			}
		});

		panelZ.add(buttonRotarZIzda);
		panelZ.add(buttonRotarZDerech);
		
		JPanel panelReinicia = new JPanel();
		panelReinicia.setBorder(new TitledBorder("Reinicia"));

		JButton buttonReinicia = new JButton("Reiniciar");
		buttonReinicia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.reinicia();
			}
		});
		
		panelReinicia.add(buttonReinicia);
		
		JPanel panelFiguras = new JPanel();
		panelFiguras.setBorder(new TitledBorder("Figuras"));
		
		
		JButton buttonVara = new JButton("Vara");
		buttonVara.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.cambiarModo(1);
			}
		});
		panelFiguras.add(buttonVara);
		
		JButton buttonPretzel = new JButton("Pretzel");
		buttonPretzel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.cambiarModo(2);
			}
		});
		panelFiguras.add(buttonPretzel);
		
		JButton buttonObj = new JButton("Object");
		buttonObj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.cambiarModo(3);
			}
		});
		panelFiguras.add(buttonObj);

		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		
		panel.add(panelX);
		panel.add(panelY);
		panel.add(panelZ);
		panel.add(panelReinicia);
		panel.add(panelFiguras);

		buttonRotarXIzda.setFocusable(false);
		buttonRotarXDerech.setFocusable(false);
		buttonRotarYIzda.setFocusable(false);
		buttonRotarXDerech.setFocusable(false);
		buttonRotarZIzda.setFocusable(false);
		buttonRotarXDerech.setFocusable(false);
		buttonReinicia.setFocusable(false);
		buttonPretzel.setFocusable(false);
		buttonVara.setFocusable(false);
		
		JPanel panelComandos = new JPanel();
		this.getContentPane().add(panelComandos, BorderLayout.NORTH);
		
		JLabel comandos = new JLabel("Acceso rápido:     <X> <Y> <Z> para rotar a la derecha sobre el eje    <R>  para Reiniciar las rotaciones   <P>  para Pretzel y <V>  para Vara");
		panelComandos.add(comandos);

		// Dimension the JFrame to cover the canvas!!
		this.pack();
		
		//log_Canvas();
		log_JFrame();

		// Build the controller, and register it as a listener for multiple events        
		controller= new Controller(canvas);
		
		// Canvas listener
		canvas.addGLEventListener(controller);
		canvas.addKeyListener(controller);                
	}


	/////////////////////////////////  
	private void log_OpenGL(GLCapabilities caps){
		//OpenGL settings
		System.out.print("OpenGL settings\n");
		System.out.print("Double Buffered: "+ caps.getDoubleBuffered() + "\n");
		System.out.print("Hardware Accelerated: "+ caps.getHardwareAccelerated() + "\n");
		System.out.print("Color depth (RGB bits): "+ caps.getRedBits() + "\t" + caps.getGreenBits() + "\t"+ caps.getBlueBits() + "\n");
		System.out.print("Depth bits: "+ caps.getDepthBits() + "\n");
		System.out.print("\n");
	}

	/////////////////////////////////
	private void log_Canvas(){
		// Canvas
		System.out.print("Canvas properties:\n");
		System.out.print("GL-profile:\t" + canvas.getGLProfile().getName()+ "\n");
		System.out.print("Automatic buffer swapping: "+ canvas.getAutoSwapBufferMode()+ "\n");
		System.out.print("GL-orientated:\t" + canvas.isGLOriented() + "\n");
		System.out.print("Canvas size:\t" + canvas.getWidth()+ " x " +  canvas.getHeight() +"\n");
		System.out.print("\n");
	}

	/////////////////////////////////
	private void log_JFrame(){
		// Window
		System.out.print("Window's size:\n");
		System.out.print("Size:\t" + this.getWidth() + " x " +  this.getHeight() +"\n");
		System.out.print("\n");
	}


	/////////////////////////////////   
	public static void main(String[] args) {

		final SwingGUI app = new SwingGUI();
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				app.setVisible(true);		        
				// The canvas requests the focus in order to make the keyListener available from the beginning 
				// The focus must be requested after setting the canvas visible!
				app.canvas.requestFocusInWindow();  			
			}			
		});

	}

}