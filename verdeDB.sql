SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, 
FOREIGN_KEY_CHECKS=0; 
SET @OLD_SQL_MODE=@@SQL_MODE, 
SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP SCHEMA IF EXISTS verdeSRLdb;
CREATE SCHEMA IF NOT EXISTS verdeSRLdb DEFAULT CHARACTER SET utf8mb4;
USE verdeSRLdb;

/*SpeciePianta*/
DROP TABLE IF EXISTS VerdeSRLdb.SpeciePianta;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.SpeciePianta(
	CodicePianta VARCHAR(45) PRIMARY KEY NOT NULL,
	NomeLatino VARCHAR(45) UNIQUE NOT NULL,
	NomeComune VARCHAR(45) NOT NULL,
	Giacenza INT NOT NULL,
	Esotica TINYINT NOT NULL,
	Esterno TINYINT NOT NULL,
	Fiorita TINYINT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Ordine*/
DROP TABLE IF EXISTS VerdeSRLdb.Ordine;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Ordine(
	CodiceOrdine INT NOT NULL AUTO_INCREMENT,
	IndirizzoConsegna VARCHAR(45) NOT NULL,
	`Data` DATE NOT NULL,
	Stato ENUM('aperto', 'finalizzato', 'spedito', 'consegnato') NOT NULL,
	Contatto Varchar(45) NOT NULL,
	Rivendita CHAR(11) NOT NULL,
	PRIMARY KEY (CodiceOrdine),
	CONSTRAINT FK_OrdineContatto
	FOREIGN KEY (Contatto)
	REFERENCES VerdeSRLdb.Contatti (Recapito)
	ON DELETE NO ACTION,
	CONSTRAINT FK_OrdineRivendita
	FOREIGN KEY (Rivendita)
	REFERENCES VerdeSRLdb.Rivendita (PartitaIVA)
	ON DELETE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Composizione*/
DROP TABLE IF EXISTS VerdeSRLdb.Composizione;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Composizione(
	Ordine INT NOT NULL,
	SpeciePianta VARCHAR(45) NOT NULL,
	Quantita INT NOT NULL,
	PRIMARY KEY (Ordine, SpeciePianta),
	CONSTRAINT FK_ComposizioneSpecie
	FOREIGN KEY (SpeciePianta)
	REFERENCES VerdeSRLdb.SpeciePianta (CodicePianta)
	ON DELETE NO ACTION,
	CONSTRAINT FK_ComposizioneOrdine
	FOREIGN KEY (Ordine)
	REFERENCES VerdeSRLdb.Ordine(CodiceOrdine)
	ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Listino*/
DROP TABLE IF EXISTS VerdeSRLdb.Listino;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Listino(
		Pianta VARCHAR(45) NOT NULL,
        DataInizio DATE NOT NULL,
		DataFine DATE,
		Prezzo DECIMAL(7,2) NOT NULL,
        PRIMARY KEY(Pianta, DataInizio),
        FOREIGN KEY (Pianta)
		REFERENCES VerdeSRLdb.SpeciePianta (CodicePianta)
		ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

		
/*Rivendita*/
DROP TABLE IF EXISTS VerdeSRLdb.Rivendita;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Rivendita(
	PartitaIVA CHAR(11) PRIMARY KEY NOT NULL,
    Nome varchar(45),
	IndirizzoFisico VARCHAR(45) UNIQUE NOT NULL,
	IndirizzoFatturazione VARCHAR(45) UNIQUE,
	NomeReferente VARCHAR(45),
	CognomeReferente VARCHAR(45))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;	

	
/*Colorazione*/
DROP TABLE IF EXISTS VerdeSRLdb.Colorazione;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Colorazione(
	Pianta VARCHAR(45) NOT NULL,
    Colore VARCHAR(45) NOT NULL,
	PRIMARY KEY (Pianta, Colore),
	CONSTRAINT FK_ColorazionePianta
	FOREIGN KEY (Pianta)
	REFERENCES VerdeSRLdb.SpeciePianta (CodicePianta)
	ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Fornitore*/
DROP TABLE IF EXISTS VerdeSRLdb.Fornitore;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Fornitore(
	CodiceFornitore varchar(45) PRIMARY KEY NOT NULL,
	CF CHAR(11) UNIQUE NOT NULL,
	Nome VARCHAR(45) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Indirizzi*/
DROP TABLE IF EXISTS VerdeSRLdb.Indirizzi;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Indirizzi(
	Via VARCHAR(45) NOT NULL,
    NCivico INT NOT NULL,
    Citta VARCHAR(45) NOT NULL,
	Fornitore varchar(45) NOT NULL,
	PRIMARY KEY (Fornitore, Via, NCivico, Citta),
	CONSTRAINT FK_IndirizzoFornitore
	FOREIGN KEY (Fornitore)
	REFERENCES VerdeSRLdb.Fornitore(CodiceFornitore)
	ON DELETE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Rifornimento*/
DROP TABLE IF EXISTS VerdeSRLdb.Rifornimento;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Rifornimento(
	ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, 
	Fornitore varchar(45) NOT NULL,
	Pianta VARCHAR(45) NOT NULL,
    `Data` DATE NOT NULL,
	Quantita INT NOT NULL,
	CONSTRAINT FK_RifonrnimentoFornitore
	FOREIGN KEY (Fornitore)
	REFERENCES VerdeSRLdb.Fornitore (CodiceFornitore)
	ON DELETE NO ACTION,
	CONSTRAINT FK_RifornimentoPianta
	FOREIGN KEY (Pianta)
	REFERENCES VerdeSRLdb.SpeciePianta (CodicePianta)
	ON DELETE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


/*Disponibilita*/
DROP TABLE IF EXISTS VerdeSRLdb.Disponibilita;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Disponibilita(
	Fornitore varchar(45) NOT NULL,
	Pianta VARCHAR(45) NOT NULL,
	PRIMARY KEY (Fornitore, Pianta),
	CONSTRAINT FK_DisponibilitaFornitore
	FOREIGN KEY (Fornitore)
	REFERENCES VerdeSRLdb.Fornitore (CodiceFornitore)
	ON DELETE NO ACTION,
	CONSTRAINT FK_DisponibilitaPianta
	FOREIGN KEY (Pianta)
	REFERENCES VerdeSRLdb.SpeciePianta (CodicePianta)
	ON DELETE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Contatti*/
DROP TABLE IF EXISTS VerdeSRLdb.Contatti;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.Contatti(
	Recapito VARCHAR(45)  PRIMARY KEY NOT NULL,
	Mezzo  ENUM('Telefono', 'Cellulare', 'Email') NOT NULL,
    Rivendita CHAR(11) NOT NULL,
	CONSTRAINT FK_ContattiRivendita
	FOREIGN KEY (Rivendita)
	REFERENCES VerdeSRLdb.Rivendita (PartitaIVA)
	ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*User*/
DROP TABLE IF EXISTS `VerdeSRLdb`.`User`;
CREATE TABLE IF NOT EXISTS VerdeSRLdb.User (
	Username VARCHAR(45) NOT NULL,
	U_password VARCHAR(33) NOT NULL,
	Ruolo ENUM('Manager', 'Operatore', 'Magazziniere') NOT NULL,
	PRIMARY KEY (Username))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*-------------------------------------------*/
/*STORED PROCEDURES*/
/*-------------------------------------------*/

/*Login*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`login`;
DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER=`root`@`localhost` PROCEDURE login(in var_username varchar(45), in var_pass varchar(45), out var_role INT) 
	BEGIN 
		DECLARE var_user_role ENUM("MANAGER", "OPERATORE", "MAGAZZINIERE"); 

		select Ruolo from `User` where Username = var_username AND U_password = md5(var_pass)
		into  var_user_role;
		if var_user_role = 'MANAGER' then set var_role = 1; 

		elseif var_user_role = 'OPERATORE' then set var_role = 2; 

		elseif var_user_role = 'MAGAZZINIERE' then set var_role = 3;  

		else set var_role = 4; 

		END IF; 
END$$
DELIMITER ;


/*M1 (registra specie)
Operazione per inserire una nuova specie nel database.*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`registraSpecie`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `registraSpecie`(
in var_CodicePianta varchar(45), in var_NomeLatino varchar(45), in var_NomeComune varchar(45), in var_Giacenza int, 
in var_Esotica Tinyint, in var_Esterno Tinyint, in var_Colore varchar(45), in var_Prezzo DECIMAL(7,2), in var_Fornitore varchar(45))
BEGIN
		declare var_Fiorita Tinyint;
        declare exit handler for sqlexception
		begin 
			rollback; 
			resignal; 
		end; 
	/* ho tutte insert, l'unica lettura la ho nel trigger che si attiva nel caso la pianta sia fiorita, dato che non ho operazioni 
    che possono modificare una specie non mi interessa prendere alcun lock, mantengo la transazione perchè in questa procedura faccio insert 
    su un terzo delle tabelle complessive*/
		set transaction isolation level read uncommitted;
		start transaction;
			if var_Colore = ""  then
				set var_Fiorita = 0;
			else 
				set var_Fiorita = 1;
			end if;
			insert into SpeciePianta (CodicePianta, NomeLatino, NomeComune, Giacenza, Esotica, Esterno, Fiorita)
			values  (var_CodicePianta, var_NomeLatino,  var_NomeComune, var_Giacenza, var_Esotica, var_Esterno, var_Fiorita);
            if var_Fiorita = 1 then
				insert into Colorazione(Pianta, Colore) values (var_CodicePianta, var_Colore);
			end if;
			insert into Listino(DataInizio, Pianta, DataFine, Prezzo) values(NOW(), var_CodicePianta, NULL, var_Prezzo);
            insert into Disponibilita(Fornitore, Pianta) values (var_Fornitore, var_CodicePianta);
		Commit;
	END$$
DELIMITER;

/* OP5 registra Rivendita*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`registraRivendita`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER = `root`@`localhost` PROCEDURE  `registraRivendita`(
in var_partitaIVA char(11), in var_indirizzoFisico varchar(45), in var_indirizzoFatturazione varchar(45), in var_NomeRef varchar(45),
in var_CognomeRef varchar(45), in var_recapito varchar(45), in var_mezzo varchar(45)
)
BEGIN
		Insert into Rivendita(partitaIva, indirizzoFisico, indirizzoFatturazione, nomeReferente, cognomeReferente) 
		values (var_partitaIva, var_indirizzoFisico, var_indirizzoFatturazione, var_nomeRef, var_cognomeRef);
		Insert into Contatti(Recapito, Mezzo, Rivendita) values (var_recapito, var_mezzo, var_partitaIVA);
END $$
DELIMITER ;

/*  OP6 aggiungiContatto*/
USE verdeSrlDB;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`aggiungiContatto`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER = `root`@`localhost` PROCEDURE  `aggiungiContatto`(
in var_partitaIVA char(11), in var_recapito varchar(45), in var_mezzoTemp varchar(45)
)
BEGIN
	declare var_mezzo varchar(45);
    if var_mezzoTemp = 'telefono' then set var_mezzo = 1;

	elseif var_mezzoTemp = 'cellulare' then set var_mezzo = 2; 

	elseif var_mezzoTemp = 'email' then set var_mezzo = 3; 
    else signal sqlstate "45000" set message_text = "il mezzo per il recapito inserito è errato";
    end if;
    Insert into Contatti(Recapito, Mezzo, Rivendita) values (var_recapito, var_mezzo, var_partitaIVA);
END $$
DELIMITER ;

/*  OP7 mostraRivendita*/
USE verdeSrlDB;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`mostraRivendita`;
DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostraRivendita`(
IN var_partitaIVA char(11))
BEGIN
		declare exit handler for sqlexception
		begin 
			rollback; 
			resignal; 
		end; 

		set transaction isolation level read committed; 
        set transaction read only;
		start transaction;
			select * from Rivendita where partitaIVA = var_partitaIVA;
            select Recapito, Mezzo from Contatti where Rivendita = var_partitaIVA;
		commit;
END $$
DELIMITER;

/*M2(Modifica Prezzo)*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`modificaPrezzo`;
DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaPrezzo`(IN var_SpeciePianta varchar(45), IN var_NuovoPrezzo decimal(7, 2),
	OUT outcome tinyint)
BEGIN
		declare var_PrezzoCorrente Decimal(7,2);
		declare var_NuovaData DATE;
		declare exit handler for sqlexception
		begin 
			rollback; 
			resignal; 
		end; 

		set transaction isolation level read committed; 
		start transaction;
			Select Prezzo 
			From Listino where Pianta = var_SpeciePianta and DataFine  is NULL
			Into var_PrezzoCorrente;

				if var_NuovoPrezzo <> var_PrezzoCorrente then
					set var_NuovaData = NOW();
					Update Listino
					Set DataFine = var_NuovaData
					Where Pianta = var_SpeciePianta and DataFine is NULL;
	
					Insert into Listino(Pianta, DataInizio, DataFine, Prezzo) values (var_SpeciePianta, var_NuovaData, NULL, var_NuovoPrezzo);
					set outcome =1;
				Else set outcome = 0;
			End if;

		 Commit;
	END $$
DELIMITER;



/* M4 aggiungiIndirizzo */
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`aggiungiIndirizzo`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER = `root`@`localhost` PROCEDURE  `aggiungiIndirizzo`(
in var_via char(11), in var_nCivico int, in var_citta varchar(45), in var_fornitore varchar(45)
)
BEGIN
    Insert into Indirizzi(Via, NCivico, Citta, Fornitore) values (var_via, var_nCivico, var_citta, var_fornitore);
END $$
DELIMITER ;


/*MAG2 registra rifornimento*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`registraRifornimento`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registraRifornimento`(in var_Fornitore Varchar(45), in var_Pianta Varchar(45), in var_Quantita INT)
BEGIN	
		declare var_verifica_disponibilita int;
		declare exit handler for sqlexception
		begin
			rollback;
			resignal;
		end;
		set transaction isolation level read committed;
		start transaction;
			select count(*)
			from disponibilita
			where Pianta = var_Pianta and Fornitore = var_Fornitore
            into var_verifica_disponibilita;
			if  var_verifica_disponibilita = 0 then
				signal sqlstate "45000" set message_text = "Specie non disponibile presso il fornitore scelto";
			else
				Insert into Rifornimento(Fornitore, Pianta, `Data`, Quantita) values (var_Fornitore, var_Pianta, NOW(), var_Quantita);
				Update SpeciePianta
				Set Giacenza = Giacenza+var_Quantita
				where CodicePianta = var_Pianta;
			end if;
		commit;

	END$$
DELIMITER ;

/* OP1 apri ordine*/ 
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`apriOrdine`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `apriOrdine`(
in var_Rivendita CHAR(11), in var_IndirizzoConsegna Varchar(45), in var_Contatto varchar(45), in var_Specie varchar(45), in var_Quantita INT, out var_Codice INT)
BEGIN	
    	declare exit handler for sqlexception 
		begin 
			rollback; 
			resignal; 
		end; 

		set transaction isolation level repeatable read;  /*per colpa del trigger*/
		start transaction;
			Insert into Ordine(IndirizzoConsegna, `Data`, Stato, Contatto, Rivendita) values(var_IndirizzoConsegna, NOW(), 1, var_Contatto, var_rivendita);
			Select MAX(CodiceOrdine) from Ordine into var_Codice;
			Insert into Composizione(Ordine, SpeciePianta, Quantita) values (var_Codice, var_Specie, var_Quantita );
		Commit;
	END$$
DELIMITER;

/*OP2 aggiungi Specie*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`aggiungiSpecieOrdine`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungiSpecieOrdine`(
in var_CodiceOrdine INT, in var_SpeciePianta VARCHAR(45), in var_Quantita INT )
BEGIN
	declare exit handler for sqlexception 
		begin 
			rollback; 
			resignal; 
		end; 

		set transaction isolation level repeatable read;  /*per colpa del trigger, non voglio che nessuno modifichi la giacenza mentre il trigger verifica la disponibilità*/
		start transaction;
			insert into Composizione(Ordine, SpeciePianta, Quantita) values (var_CodiceOrdine, var_SpeciePianta, var_quantita); 
		Commit;
	END$$
DELIMTER;

/*OP3 finalizza Ordine*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`modificaStatoOrdine`;

DELIMITER $$
USE verdeSRLdb $$
CREATE DEFINER=`root`@`localhost` PROCEDURE modificaStatoOrdine(in var_CodiceOrdine INT, in var_Stato varchar(45))
	BEGIN 
		update Ordine 	
		Set Stato = var_Stato
		Where CodiceOrdine = var_CodiceOrdine;
	END $$
DELIMITER;

/*MAG1 report giacenze*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`reportGiacenze`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportGiacenze`(in var_quantita INT)
BEGIN	
		declare done INT DEFAULT FALSE;
		declare var_codicePianta_cur varchar(45);

		declare cur cursor for 
			select CodicePianta
			from SpeciePianta 
			where giacenza <= var_quantita;
			
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
		begin 
			rollback;
			resignal;
		end;
		set transaction isolation level serializable; /* ho il cursore definito su una range query, nel caso in cui un manager inserisse una nuova specie che ricade nella range query avrei una phantom read*/  
        
       
	DROP TEMPORARY TABLE IF EXISTS lista_giacenze;
    CREATE TEMPORARY TABLE lista_giacenze (
		Codice varchar(45),
        Nome varchar(93),
        Giacenza int);
	start transaction;
		open cur;
		read_loop: loop
			fetch cur into var_codicePianta_cur;
			if done then
				leave read_loop;
			end if;
			insert into lista_giacenze
				select	CodicePianta as Codice, 
					CONCAT(NomeComune, " (", NomeLatino, + ")") as Nome, 
					Giacenza
                    from SpeciePianta left join disponibilita on Pianta = CodicePianta
                    where codicePianta = var_CodicePianta_cur;
		end loop;
		close cur;
		select * from lista_giacenze;

		open cur;
		set done = false;
		read_loop: loop
			fetch cur into var_codicePianta_cur;
			if done then
				leave read_loop;
			end if;
			select  CF, Nome
            from Disponibilita join Fornitore on Fornitore = CodiceFornitore
            where Pianta = var_codicePianta_cur;
		end loop;
		close cur;
		commit;
	END$$
DELIMITER;
	
/* MAG3 mostraOrdini*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`mostraOrdini`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostraOrdini`()
BEGIN	
	/*no transaction perchè un ordine finalizzato non può piu essere modificato*/
        Select codiceOrdine, indirizzoConsegna, `Data`, Contatto, Rivendita from Ordine where Stato = "Finalizzato";
END$$
DELIMITER;
    
    /* MAG4 mostraComposizione*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`mostraComposizione`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `mostraComposizione`(in var_Codice int)
BEGIN	
	/*non posso modificare ne la composzione di un ordine già finalizzato ne una specie (apparte modificare il prezzo, 
    che però non è una info che viene letta qui*/
        Select CONCAT(NomeComune, " (", NomeLatino, + ")") 	as Nome,
						Composizione.Quantita 			as Quantita 
				from SpeciePianta join Composizione on CodicePianta = SpeciePianta 
                join Ordine on Composizione.Ordine = Ordine.CodiceOrdine
				where Ordine.Stato = "Finalizzato" and Composizione.Ordine = var_Codice;
       
END$$
DELIMITER;

/*M3 report prezzi*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`reportPrezzi`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportPrezzi`(in var_pianta varchar(45), in var_range DATE)
BEGIN	
			declare exit handler for sqlexception
		
        begin	
			rollback;
			resignal;
		end;
        /* visto che ho una range query, potrei inserire un prezzo e quello vecchio potrebbe ricadere nela range della query*/
		set transaction isolation level serializable;
		set transaction read only; 
		start transaction;
		
			select *
			from Listino 
			where Pianta = var_pianta and DataFine <= var_range;
			
		commit;
		
	END$$
DELIMITER $$;

/* M4 aggiungi colorazione*/
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`aggiungiColorazione`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiungiColorazione`(in var_CodicePianta varchar(45), in var_Colorazione varchar(45))
BEGIN
	DeCLARE var_fiorita tinyint;
	declare exit handler for sqlexception
        begin	
			rollback;
			resignal;
		end;
        /* ho un trigger che va a leggere un attributi di SpeciePianta prima di ogni inserimento, non ho messo una transazione perchè
        non ho modo di modificare l'attributo della specie che va a leggere il trigger, quindi non avrei anomalie per letture concorrenti dello stesso attributo "Fiorita"*/
				insert into Colorazione values (var_CodicePianta, var_Colorazione);
    
END $$
DELIMITER ;



/*OP4 reportOrdine */
USE verdeSRLdb;
DROP PROCEDURE IF EXISTS `verdeSRLdb`.`reportOrdine`;

DELIMITER $$
USE verdeSRLdb$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportOrdine`(in var_CodiceOrdine INT)
BEGIN
		declare done INT DEFAULT FALSE;
		declare var_codicePianta_cur varchar(45);
		declare var_dataOrdine DATE;
		declare cur cursor for 
			select CodicePianta
			from speciePianta join Composizione on CodicePianta = SpeciePianta
			where Ordine = var_codiceOrdine;
			
		declare continue handler for not found set done = true;
		declare exit handler for sqlexception
		begin 
			rollback;
			resignal;
		end;
			DROP TEMPORARY TABLE IF EXISTS dettagli_specie;
			CREATE TEMPORARY TABLE dettagli_specie(
				Codice varchar(45),
				Nome VARCHAR(93),
				Quantita INT,
				prezzo_unita DECIMAL(7,2),
				prezzo_cumulativo DECIMAL(7,2),
                Primary Key (Codice)
			);
            
		set transaction isolation level SERIALIZABLE; /* potrei avere un manager che modifica il prezzo di una specie in concomitanza al report di un ordine che contiene la stessa specie*/
		start transaction;
			/*prendo la data dell'ordine per trovare il prezzo corretto*/
			select `Data`
			from Ordine
			where CodiceOrdine = var_codiceOrdine
			into var_dataOrdine;
			
			open cur;
			read_loop: loop
				fetch cur into var_codicePianta_cur;
				if done then
					leave read_loop;
				end if;
				insert into dettagli_specie
					select SpeciePianta.CodicePianta as Codice,
							CONCAT(NomeComune, " (", NomeLatino, ")") 	as Nome,
							Composizione.Quantita 							as Quantita,
							Listino.Prezzo 									as prezzo_unita,
							Listino.prezzo * Composizione.Quantita 			as prezzo_cumulativo
							from SpeciePianta join Composizione on CodicePianta = SpeciePianta 
							join Listino on Listino.Pianta = SpeciePianta.CodicePianta
							where Composizione.speciePianta = var_CodicePianta_cur and DataInizio<=var_DataOrdine /*una variazione del prezzo non ha effetto su un ordine già finalizzato*/
                            order by DataInizio desc 		
							Limit 1; 					 
			end loop;
			close cur;
			
			select	*
			from	Ordine
			where CodiceOrdine = var_CodiceOrdine;
			
			select * from dettagli_specie;
			
			select	SUM(Quantita) as QuantitaTotale,
					SUM(prezzo_cumulativo) AS PrezzoTotale
			from	dettagli_specie;
        
			drop temporary table dettagli_specie;
		commit;
	END $$
    DELIMITER ;

/*-------------------------------------------------------------------------*/
SET SQL_MODE = '';
DROP USER IF EXISTS login;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'login' IDENTIFIED BY 'login';
GRANT EXECUTE ON PROCEDURE verdeSRLdb.Login TO login;


SET SQL_MODE = '';
DROP USER IF EXISTS manager;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'manager' IDENTIFIED BY 'manager';
GRANT EXECUTE ON PROCEDURE verdeSRLdb.modificaPrezzo to manager;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.registraSpecie to manager;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.reportPrezzi to manager;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.aggiungiIndirizzo to manager;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.aggiungiColorazione to manager;

SET SQL_MODE = '';
DROP USER IF EXISTS magazziniere;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'magazziniere' IDENTIFIED BY 'magazziniere';
GRANT EXECUTE ON PROCEDURE verdeSRLdb.registraRifornimento to magazziniere;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.reportGiacenze to magazziniere;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.mostraOrdini to magazziniere;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.mostraComposizione to magazziniere;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.modificaStatoOrdine to magazziniere;

SET SQL_MODE = '';
DROP USER IF EXISTS operatore;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'operatore' IDENTIFIED BY 'operatore';
GRANT EXECUTE ON PROCEDURE verdeSRLdb.apriOrdine to operatore;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.registraRivendita to operatore;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.mostraRivendita to operatore;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.aggiungiContatto to operatore;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.aggiungiSpecieOrdine to operatore;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.modificaStatoOrdine to operatore;
GRANT EXECUTE ON PROCEDURE verdeSRLdb.reportOrdine to operatore;


/*trigger*/
Delimiter $$
USE verdeSRLdb $$
DROP TRIGGER IF EXISTS VerdeSRLdb.Colorazione_BEFORE_INSERT$$
CREATE DEFINER = CURRENT_USER TRIGGER VerdeSRLdb.Colorazione_BEFORE_INSERT
BEFORE INSERT ON Colorazione FOR EACH ROW
BEGIN	
	declare var_fiorita TINYINT;
	
	select Fiorita 
	from SpeciePianta
	where CodicePianta = NEW.Pianta
	into var_fiorita;
	
	if var_fiorita = 0 then
		signal sqlstate '45000' set message_text = 'Piante verdi non possono avere colorazioni disponibili!';
	end if;
END$$

USE verdeSRLdb $$
DROP TRIGGER IF EXISTS VerdeSRLdb.Composizione_AFTER_INSERT$$
CREATE DEFINER = CURRENT_USER TRIGGER VerdeSRLdb.Composizione_AFTER_INSERT
AFTER INSERT ON Composizione FOR EACH ROW
BEGIN
	UPDATE 	SpeciePianta
    SET 	Giacenza = Giacenza - NEW.Quantita 
    WHERE 	CodicePianta = NEW.SpeciePianta;
END$$

USE verdeSRLdb $$
DROP TRIGGER IF EXISTS VerdeSRLdb.Composizione_BEFORE_INSERT$$
CREATE DEFINER = CURRENT_USER TRIGGER VerdeSRLdb.Composizione_BEFORE_INSERT
BEFORE INSERT ON Composizione FOR EACH ROW
BEGIN
	declare var_Giacenza int;
    select Giacenza from SpeciePianta  where CodicePianta = NEW.SpeciePianta into var_Giacenza;
    if var_Giacenza < NEW.Quantita then
		SIGNAL sqlstate '45000' set message_text = "La quantità inserita eccede la giacenza in magazzino";
    end if;
END$$
DELIMITER ;

/* ------------------------------------------------------------------------------------------ */
/*eventi*/

SET GLOBAL event_scheduler = on;

DELIMITER $$
CREATE EVENT IF NOT EXISTS pulizia_dati
	On schedule EVERY 1 YEAR on completion preserve comment "rimozione delle tuple di rifornimento e listino memorizzate da più di due anni"
	Do BEGIN
    DELETE from Ordine where Data < (NOW() - INTERVAL 2 YEAR);
		DELETE from Rifornimento where Data < (NOW() - INTERVAL 2 YEAR);
		DELETE from Listino where DataFine < (NOW() - INTERVAL 2 YEAR);
		END$$
DELIMITER ;


/* ------------------------------------------------------------------------------------------ */
INSERT INTO speciepianta VALUES ("a1", "Primula Vulgaris", "Primula Comune", 800, 0, 1, 1), 
								("a2", "Opuntia ficus-indica ", "Fico d'india", 600, 0, 1, 0), 
                                ("a3", "Rosa Gallica", "Rosa Serpeggiante", 1500, 0, 1, 1),
                                ("a4", "Citrus limon", "Limone", 400, 0, 1, 0 ),
                                ("a5", "Dypsis lutescens", "Palma di areca", 1200, 1, 0, 0 ),
                                ("a6", "Cycas revoluta L.", "Cycas", 1400, 1, 1, 0),
                                ("a7", "Cyclamen repandum", "Ciclamino", 1600, 0, 1, 1);
                                
INSERT INTO colorazione values ("a1", "Bianco"), ( "a1", "Rosa"), ( "a1", "Viola"), 
								("a3", "Rosa"), ("a3", "Bianca"),
                                ( "a7", "Rosso"), ("a7", "Bianco"), ("a7", "Viola");
                         
INSERT INTO listino values ("a1", '2022-09-01' ,NULL, 9.99),
							("a2", '2022-08-17',NULL, 21),
							("a3", '2023-02-13', NULL, 19.99),
                            ("a4", '2023-01-15', NULL, 25.69),
							("a5", '2022-10-23', NULL, 32.99),
                            ("a6", '2022-10-26', NULL, 41.20),
                            ("a7", '2022-12-10', NULL, 9.99),
                            ("a3", '2022-06-15', '2023-02-13', 14.99),
                            ("a3", '2023-04-17', '2023-06-15', 20),
                            ("a7", '2022-10-23', '2022-12-10', 6.99);
                      
INSERT INTO fornitore VALUES ("f001", "01234567891", "PippoSRL"),
							("c002", "23456789110", "PlutoSRL"),
                            ("d003", "59876543217", "PaperinoSRL");
INSERT INTO Indirizzi VALUES ("Via Luigi", 34, "RM", "f001"),
							("Via Mario", 12, "TA",  "f001"), 
                            ("Via Ermenegildo", 41, "TO" , "c002"), 
                            ("Via Margherita", 32, "MI", "c002"), 
                            ("Via Aldo Moro", 21, "PD", "d003"), 
                            ("Via Piersanti Mattarella", 45, "MI",  "d003"), 
                            ("Via Cambrige 21", 1, "BR", "d003");		

INSERT INTO Rivendita VALUES ('86334519757',"topolino srl", "Via Minnie 23 RM", "Via Paperina 12 RM", "Giacinto", "Pinto"),
							('52413592665', "Mary srl", "Via Robert Graves RM", "Via Malfoy 23 RM", "Roberto", "Minchiari" ),
                            ('12345678934', "Hash srl" , "Via pluto 45 TO", "Via Pippo 31, TO", "Ramarro", "Marrone");
							
INSERT INTO  Contatti VALUES ("3927843338", "Cellulare", "86334519757"),
							("3897645332", "Cellulare", "86334519757"),
                            ("potter@gmail.com", "Email", "86334519757"),
                            ("3287641342", "Cellulare", "52413592665"),
                            ( "067834567", "Telefono", "12345678934");
                            
INSERT INTO disponibilita(Fornitore , Pianta) VALUES ("f001", "a1"),
								("f001", "a2"),
                                ("c002", "a4"),
                                ("c002", "a5");
                                
INSERT INTO Ordine VALUES(1, "via Gregorio VII 45, RM", '2023-04-17', 1, 3927843338 ,86334519757 ),
						(2, "via Giuseppina 78, RM", NOW(), 2, 3897645332 ,86334519757 ),
                        (3, "via Pluto 5, RM", NOW(), 2, 067834567 ,12345678934 ),
                        (4, "via della Madonna Incoronata 5, TO", NOW(), 3, 067834567 ,12345678934);
INSERT INTO Composizione VALUES (1,"a1", 100),
								(2,"a2", 200), 
                                (3, "a3", 300),
								(3, "a4", 100),
                                (3, "a7", 200),
                                (3, "a5", 36),
                                (4,"a5", 50);
		
INSERT INTO `User` VALUES ("Aldo", md5("leonardo"), "Magazziniere"), 
							("Giovanni", md5("leonardo"), "Operatore"), 
                            ("Giacomo", md5("leonardo"), "Manager");
