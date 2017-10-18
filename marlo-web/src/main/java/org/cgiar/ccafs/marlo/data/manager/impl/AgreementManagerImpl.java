/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/


package org.cgiar.ccafs.marlo.data.manager.impl;

import org.cgiar.ccafs.marlo.data.dao.AgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.CountryAgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.CrpAgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.FundingSourceAgreementDAO;
import org.cgiar.ccafs.marlo.data.dao.PlaAgreementDAO;
import org.cgiar.ccafs.marlo.data.manager.AgreementManager;
import org.cgiar.ccafs.marlo.data.model.Agreement;
import org.cgiar.ccafs.marlo.data.model.CountryAgreement;
import org.cgiar.ccafs.marlo.data.model.CrpAgreement;
import org.cgiar.ccafs.marlo.data.model.FundingSourceAgreement;
import org.cgiar.ccafs.marlo.data.model.PlaAgreement;
import org.cgiar.ccafs.marlo.data.model.dto.AgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.CountryAgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.CrpAgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.FundingSourceAgreementDTO;
import org.cgiar.ccafs.marlo.data.model.dto.PlaAgreementDTO;
import org.cgiar.ccafs.marlo.mappers.AgreementMapper;
import org.cgiar.ccafs.marlo.mappers.CountryAgreementMapper;
import org.cgiar.ccafs.marlo.mappers.CrpAgreementMapper;
import org.cgiar.ccafs.marlo.mappers.FundingSourceAgreementMapper;
import org.cgiar.ccafs.marlo.mappers.PlaAgreementMapper;

import java.util.Iterator;

import com.google.inject.Inject;


public class AgreementManagerImpl implements AgreementManager {

  private AgreementDAO agreementDAO;
  private CountryAgreementDAO countryAgreement;
  private CrpAgreementDAO crpAgreement;
  private PlaAgreementDAO plaAgreement;
  private FundingSourceAgreementDAO fsAgreement;


  @Inject
  public AgreementManagerImpl(AgreementDAO agreementDAO, CountryAgreementDAO countryAgreement,
    CrpAgreementDAO crpAgreement, PlaAgreementDAO plaAgreement) {
    this.agreementDAO = agreementDAO;
    this.countryAgreement = countryAgreement;
    this.crpAgreement = crpAgreement;
    this.plaAgreement = plaAgreement;
  }


  /**
   * this method load an agreement form database given its code
   * 
   * @author Julián Rodríguez
   * @date 20171011
   * @param codAgreement - the code of the agreement
   * @return an AgreementDTO object
   */
  @Override
  public AgreementDTO loadAgreement(String codAgreement) {
    AgreementDTO agreementDTO = null;
    Agreement agreement = this.agreementDAO.find(codAgreement);

    if (agreement != null) {
      agreementDTO = AgreementMapper.INSTANCE.agreementToAgreementDTO(agreement);
    }

    return agreementDTO;
  }

  /**
   * load an agreement relationship with the funding source id and the code of
   * the agreement
   * 
   * @author Julián Rodríguez Calderón CCAFS/CIAT
   * @since 20171018
   * @param codAgreement code of the agreement
   * @param idFundingSource id of the funding source
   * @return a DTO object
   */
  @Override
  public FundingSourceAgreementDTO loadFSAgreement(String codAgreement, String idFundingSource) {
    FundingSourceAgreementDTO fsAgreementDTO = null;
    FundingSourceAgreement fsAgreement = this.fsAgreement.find(codAgreement, idFundingSource);

    if (fsAgreement != null) {
      fsAgreementDTO =
        FundingSourceAgreementMapper.INSTANCE.fundingSourceAgreementToFundingSourceAgreementDTO(fsAgreement);
    }

    return fsAgreementDTO;
  }


  /**
   * this method saves or updates a new agreement into the database
   * 
   * @author Julián Rodríguez
   * @date 20171011
   * @param agreement - this is an AgreementDTO object
   * @return an string with the id of the agreement into the database
   */
  @Override
  public String saveAgreement(AgreementDTO agreement) {
    String codAgreement = null;
    Agreement agreementDB = AgreementMapper.INSTANCE.agreementDTOToAgreement(agreement);

    // save or update the agreement
    if (agreement.isNew()) {
      codAgreement = this.agreementDAO.save(agreementDB);
    } else {
      codAgreement = this.agreementDAO.update(agreementDB);
    }

    // Saving the countries of the agreement
    Iterator iterCountries = agreement.getCountriesAgreements().iterator();

    if (iterCountries != null) {
      while (iterCountries.hasNext()) {
        CountryAgreementDTO theCountry = (CountryAgreementDTO) iterCountries.next();

        CountryAgreement country = CountryAgreementMapper.INSTANCE.countryAgreementDTOToCountryAgreement(theCountry);

        // save or update the country
        if (agreement.isNew()) {
          countryAgreement.save(country);
        } else {
          countryAgreement.update(country);
        }

      }
    }

    // saving the crps of the agreement
    Iterator iterCrps = agreement.getCrpsAgreements().iterator();

    if (iterCrps != null) {
      while (iterCrps.hasNext()) {
        CrpAgreementDTO theCrp = (CrpAgreementDTO) iterCrps.next();

        CrpAgreement crp = CrpAgreementMapper.INSTANCE.crpAgreementDTOToCrpAgreement(theCrp);

        // save or update the crp
        if (agreement.isNew()) {
          crpAgreement.save(crp);
        } else {
          crpAgreement.update(crp);
        }
      }
    }


    // saving the plas of the agreement
    Iterator iterPlas = agreement.getPlasAgreements().iterator();

    if (iterPlas != null) {
      while (iterPlas.hasNext()) {
        PlaAgreementDTO thePla = (PlaAgreementDTO) iterPlas.next();

        PlaAgreement pla = PlaAgreementMapper.INSTANCE.plaAgreementDTOToPlaAgreement(thePla);

        // save or update the pla
        if (agreement.isNew()) {
          plaAgreement.save(pla);
        } else {
          plaAgreement.update(pla);
        }

      }
    }


    return codAgreement;

  }

  /**
   * this method saves or update a new funding source agreement into the database
   * 
   * @author Julián Rodríguez Calderón
   * @since 20171018
   * @param fsAgreement an DTO object to save
   * @return String with the id of the object
   */
  @Override
  public String saveFSAgreement(FundingSourceAgreementDTO fsAgreement) {
    FundingSourceAgreement fsAgreementDB = null;
    String idFsAgreement = null;

    fsAgreementDB =
      FundingSourceAgreementMapper.INSTANCE.fundingSourceAgreementDTOToFundingSourceAgreement(fsAgreement);

    if (fsAgreement.getId() != null) {
      idFsAgreement = this.fsAgreement.update(fsAgreementDB);
    } else {
      idFsAgreement = this.fsAgreement.save(fsAgreementDB);
    }

    return idFsAgreement;

  }


}
