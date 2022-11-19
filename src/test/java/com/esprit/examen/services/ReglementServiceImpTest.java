package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;

@ExtendWith(MockitoExtension.class)
class ReglementServiceImpTest {

	@Mock
	ReglementRepository reglementRepository;
	@InjectMocks
	ReglementServiceImpl reglementServiceImpl;
	
	//initializer des objet
	Reglement reglement= new Reglement ((long)1,(float)2.365,(float)3.252,true,new Date(),null);

	List<Reglement> r = new ArrayList<Reglement>() {
		
		{
			add(new Reglement((long)2,(float)2.365,(float)3.252,true,new Date(),null));
			add(new Reglement((long)3,(float)2.365,(float)3.252,true,new Date(),null));
			add(new Reglement((long)4,(float)2.365,(float)3.252,true,new Date(),null));
		}
	};
	
	
	@Test
	void retrieveAllReglements() {
		Mockito.doReturn(r).when(reglementRepository).findAll();
		List<Reglement> reg = reglementServiceImpl.retrieveAllReglements();
		Assertions.assertNotNull(reg);
	}

	@Test
	void addReglement() {
		Reglement reg=new Reglement();
		Mockito.when(reglementRepository.save(Mockito.any(Reglement.class))).thenReturn(reg);
		Reglement reglement = reglementServiceImpl.addReglement(reg);
		Assertions.assertNotNull(reglement);
	}

	@Test
	void retrieveReglement() {
		Mockito.when(reglementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reglement));
		Reglement reglement1=reglementServiceImpl.retrieveReglement((long)2);
		Assertions.assertNotNull(reglement1);
	}

	@Test
	void retrieveReglementByFacture() {
		Mockito.when(reglementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(reglement));
		Reglement reglement1=reglementServiceImpl.retrieveReglement((long)2);
		Assertions.assertNotNull(reglement1);

	}

	/*@Test
	void getChiffreAffaireEntreDeuxDate() {
		Mockito.doReturn(r).when(reglementRepository).findAll();
		float reg = reglementServiceImpl.getChiffreAffaireEntreDeuxDate(new Date(),new Date());
		Assertions.assertNotNull(reg);
	}*/
}
