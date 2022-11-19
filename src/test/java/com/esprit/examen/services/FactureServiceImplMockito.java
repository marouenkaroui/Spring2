package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
@ExtendWith(MockitoExtension.class)
class FactureServiceImplMockito {

	
	@Mock
	FactureRepository factureRepository;
	@InjectMocks
	FactureServiceImpl factureServiceImpl;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	//Date datefact = dateFormat.parse("30/09/2000");
	//initializer des objet
	Facture facture=new Facture ((long)1,(float)12.36,(float)10.33,null,null,false);
	List<Facture> f = new ArrayList<Facture>() {
		
		{
			add(new Facture((long)1,(float)12.36,(float)10.33,null,null,false));
			add(new Facture((long)1,(float)12.36,(float)10.33,null,null,false));
			add(new Facture((long)1,(float)12.36,(float)10.33,null,null,false));
		}
	};
Fournisseur fourn=new Fournisseur((long)1,"test","test",null,null,null,null);
	@Test
	void retrieveAllFactures(){
		Mockito.doReturn(f).when(factureRepository).findAll();
		List<Facture> fact = factureServiceImpl.retrieveAllFactures();
		Assertions.assertNotNull(fact);
	}

	
	@Test
	void addFacture() {
	Facture fact= new Facture();
	Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(fact);
	Facture facture = factureServiceImpl.addFacture(fact);
	Assertions.assertNotNull(facture);
	}

	/*@Test
	void cancelFacture() {
		Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(facture);
		facture.setArchivee(true);
		Facture f=factureServiceImpl.cancelFacture((long)1);
		Assertions.assertNotNull(factureServiceImpl.cancelFacture((long)1));
		assertEquals(true, facture.getArchivee());

	}*/
	
	@Test
	void retrieveFacture() {
		Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(facture));
		Facture fact = factureServiceImpl.retrieveFacture((long)2);
		Assertions.assertNotNull(fact);	
		
	}
	
/*	@Test
	void getFacturesByFournisseur(){
		Mockito.doReturn(f).when(factureRepository).findAll();
		Facture fact = (Facture) factureServiceImpl.getFacturesByFournisseur(fourn.getIdFournisseur());
		Assertions.assertNotNull(fact);
	}
*/
	/*@Test
	void assignOperateurToFacture() {
		Facture f=new Facture();
		//Mockito.doReturn(f).when(factureRepository).findAll();
		Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(f);
		factureServiceImpl.assignOperateurToFacture(null,null);
		Assertions.assertNotNull(facture);

	
	}*/
	
	/*@Test
	float pourcentageRecouvrement(Date startDate, Date endDate) {
		Mockito.doReturn(f).when(factureRepository).findAll();
		return 0 ;
	}
*/
	
}
