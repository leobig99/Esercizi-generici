/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morracinese2.pkg0;

import ModelloMorraCinese.MorraCineseDifficile;
import ModelloMorraCinese.MorraCineseImpossibile;
import ModelloMorraCinese.MorraCineseMedium;
import ModelloMorraCinese.MorraCineseNormale;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**

 @author andrea
 */
public class FXMLDocumentController implements Initializable {

	public static final int NORMALE = 0;

	public static final int MEDIO = 1;

	public static final int DIFFICILE = 2;

	public static final int IMPOSSIBILE = 3;

	private MorraCinese20 m;

	@FXML
	private Label risultato;

	@FXML
	private ImageView sceltaG;

	@FXML
	private ImageView sceltaPC;

	@FXML
	private Label punteggioGiocatore;

	@FXML
	private Label punteggioPC;

	@FXML
	private Label app;

	@FXML
	private Label app1;

	@FXML
	Image imCarta;

	@FXML
	Image imSasso;

	@FXML
	Image imForbice;

	@FXML
	Image imVuota;

	private final MorraCineseNormale mc = new MorraCineseNormale();
	private final MorraCineseMedium mm = new MorraCineseMedium();
	private final MorraCineseDifficile md = new MorraCineseDifficile();
	private final MorraCineseImpossibile mi = new MorraCineseImpossibile();

	private int difficoltà;

	public String nome = "";

	@FXML
	protected void MossaCarta(ActionEvent event) {
		sceltaPC.setVisible(true);
		sceltaG.setVisible(true);
		System.out.println(difficoltà);
		if (difficoltà == 3) {
			risultato.setText(mi.mossaGiocatore(0));
			punteggioPC.setText("" + mi.puntiPC);
			punteggioGiocatore.setText(nome + " " + mi.puntiG);
			sceltaG.setImage(imCarta);
			if (mi.mossaPC == MorraCineseImpossibile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mi.mossaPC == MorraCineseImpossibile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mi.mossaPC == MorraCineseImpossibile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		} else if (difficoltà == 2) {
			risultato.setText(md.mossaGiocatore(0));
			punteggioPC.setText("" + md.puntiPC);
			punteggioGiocatore.setText(nome + " " + md.puntiG);
			sceltaG.setImage(imCarta);
			if (md.mossaPC == MorraCineseDifficile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (md.mossaPC == MorraCineseDifficile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (md.mossaPC == MorraCineseDifficile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		} else if (difficoltà == 1) {
			risultato.setText(mm.mossaGiocatore(0));
			punteggioPC.setText("" + mm.puntiPC);
			punteggioGiocatore.setText(nome + " " + mm.puntiG);
			sceltaG.setImage(imCarta);
			if (mm.mossaPC == MorraCineseImpossibile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mm.mossaPC == MorraCineseImpossibile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mm.mossaPC == MorraCineseImpossibile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		} else {
			risultato.setText(mc.mossaGiocatore(0));
			punteggioPC.setText("" + mc.puntiPC);
			punteggioGiocatore.setText(nome + " " + mc.puntiG);
			sceltaG.setImage(imCarta);
			if (mc.mossaPC == MorraCineseNormale.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mc.mossaPC == MorraCineseNormale.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mc.mossaPC == MorraCineseNormale.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		}
	}

	@FXML
	protected void MossaSasso(ActionEvent event) {
		sceltaPC.setVisible(true);
		sceltaG.setVisible(true);
		if (difficoltà == 3) {
			risultato.setText(mi.mossaGiocatore(1));
			punteggioPC.setText("" + mi.puntiPC);
			punteggioGiocatore.setText(nome + " " + mi.puntiG);
			sceltaG.setImage(imSasso);
			if (mi.mossaPC == MorraCineseImpossibile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mi.mossaPC == MorraCineseImpossibile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mi.mossaPC == MorraCineseImpossibile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		} else if (difficoltà == 2) {
			risultato.setText(md.mossaGiocatore(1));
			punteggioPC.setText("" + md.puntiPC);
			punteggioGiocatore.setText(nome + " " + md.puntiG);
			sceltaG.setImage(imSasso);
			if (md.mossaPC == MorraCineseDifficile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (md.mossaPC == MorraCineseDifficile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (md.mossaPC == MorraCineseDifficile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		} 
		else if (difficoltà == 1) {
			risultato.setText(mm.mossaGiocatore(0));
			punteggioPC.setText("" + mm.puntiPC);
			punteggioGiocatore.setText(nome + " " + mm.puntiG);
			sceltaG.setImage(imSasso);
			if (mm.mossaPC == MorraCineseImpossibile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mm.mossaPC == MorraCineseImpossibile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mm.mossaPC == MorraCineseImpossibile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		}else {
			risultato.setText(mc.mossaGiocatore(1));
			punteggioPC.setText("" + mc.puntiPC);
			punteggioGiocatore.setText(nome + " " + mc.puntiG);
			sceltaG.setImage(imSasso);
			if (mc.mossaPC == MorraCineseNormale.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mc.mossaPC == MorraCineseNormale.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mc.mossaPC == MorraCineseNormale.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		}
	}

	@FXML
	protected void MossaForbici(ActionEvent event) {
		sceltaPC.setVisible(true);
		sceltaG.setVisible(true);
		if (difficoltà == 3) {
			risultato.setText(mi.mossaGiocatore(2));
			punteggioPC.setText("" + mi.puntiPC);
			punteggioGiocatore.setText(nome + " " + mi.puntiG);
			sceltaG.setImage(imForbice);
			if (mi.mossaPC == MorraCineseImpossibile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mi.mossaPC == MorraCineseImpossibile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mi.mossaPC == MorraCineseImpossibile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		} else if (difficoltà == 2) {
			risultato.setText(md.mossaGiocatore(2));
			punteggioPC.setText("" + md.puntiPC);
			punteggioGiocatore.setText(nome + " " + md.puntiG);
			sceltaG.setImage(imForbice);
			if (md.mossaPC == MorraCineseDifficile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (md.mossaPC == MorraCineseDifficile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (md.mossaPC == MorraCineseDifficile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		}else if (difficoltà == 1) {
			risultato.setText(mm.mossaGiocatore(0));
			punteggioPC.setText("" + mm.puntiPC);
			punteggioGiocatore.setText(nome + " " + mm.puntiG);
			sceltaG.setImage(imForbice);
			if (mm.mossaPC == MorraCineseImpossibile.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mm.mossaPC == MorraCineseImpossibile.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mm.mossaPC == MorraCineseImpossibile.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		}else {
			risultato.setText(mc.mossaGiocatore(2));
			punteggioPC.setText("" + mc.puntiPC);
			punteggioGiocatore.setText(nome + " " + mc.puntiG);
			sceltaG.setImage(imForbice);
			if (mc.mossaPC == MorraCineseNormale.CARTA) {
				sceltaPC.setImage(imCarta);
			}
			if (mc.mossaPC == MorraCineseNormale.FORBICI) {
				sceltaPC.setImage(imForbice);
			}
			if (mc.mossaPC == MorraCineseNormale.SASSO) {
				sceltaPC.setImage(imSasso);
			}
			app.setText("Scelta " + nome);
			app1.setText("Scelta Computer");
		}
	}

	@FXML
	protected void Reset(ActionEvent event) {
		mc.reset();
		punteggioPC.setText("" + 0);
		punteggioGiocatore.setText(nome + " " + 0);
		sceltaPC.setVisible(false);
		sceltaG.setVisible(false);
		risultato.setText("Start");
		app.setText("");
		app1.setText("");
	}

	@FXML
	protected void close(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	protected void New(ActionEvent event) throws Exception {
		m.ReStart();
	}

	@FXML
	protected void setNormal(ActionEvent event) {
		this.difficoltà = NORMALE;
	}

	@FXML
	protected void setMedium(ActionEvent event) {
		this.difficoltà = MEDIO;
	}

	@FXML
	protected void setHard(ActionEvent event) {
		this.difficoltà = DIFFICILE;
	}

	@FXML
	protected void setImpossible(ActionEvent event) {
		this.difficoltà = IMPOSSIBILE;
	}

	@FXML
	protected void Help(ActionEvent event) throws Exception {
		m.Help();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		sceltaPC.setVisible(false);
		sceltaG.setVisible(false);
		m = new MorraCinese20();
		nome = StartFXMLController.s;
		System.out.println("document" + StartFXMLController.s);
		difficoltà = 0;
		punteggioPC.setText("" + 0);
		punteggioGiocatore.setText(nome + " " + 0);
		risultato.setText("Start");
		imForbice = new Image("file:../MorraCinese2.0/src/morracinesefx/images/forbici.png");
		imCarta = new Image("file:../MorraCinese2.0/src/morracinesefx/images/carta.png");
		imSasso = new Image("file:../MorraCinese2.0/src/morracinesefx/images/sasso.png");
		imVuota = new Image("file:../MorraCinese2.0/src/morracinesefx/images/vuota.jpg");
	}

}
