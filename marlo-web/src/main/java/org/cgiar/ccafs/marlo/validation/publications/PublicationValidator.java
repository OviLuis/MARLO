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

package org.cgiar.ccafs.marlo.validation.publications;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableDissemination;
import org.cgiar.ccafs.marlo.data.model.DeliverableMetadataElement;
import org.cgiar.ccafs.marlo.data.model.DeliverablePublicationMetadata;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.LicensesTypeEnum;
import org.cgiar.ccafs.marlo.data.model.ProjectSectionStatusEnum;
import org.cgiar.ccafs.marlo.utils.InvalidFieldsMessages;
import org.cgiar.ccafs.marlo.validation.BaseValidator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
@Named
public class PublicationValidator extends BaseValidator {


  // GlobalUnit Manager
  private GlobalUnitManager crpManager;

  @Inject
  public PublicationValidator(GlobalUnitManager crpManager) {
    super();
    this.crpManager = crpManager;
  }

  private Path getAutoSaveFilePath(Deliverable deliverable, long crpID) {
    GlobalUnit crp = crpManager.getGlobalUnitById(crpID);
    String composedClassName = deliverable.getClass().getSimpleName();
    String actionFile = ProjectSectionStatusEnum.DELIVERABLE.getStatus().replace("/", "_");
    String autoSaveFile =
      deliverable.getId() + "_" + composedClassName + "_" + crp.getAcronym() + "_" + actionFile + ".json";

    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);
  }


  public void validate(BaseAction action, Deliverable deliverable, boolean saving) {
    action.setInvalidFields(new HashMap<>());

    boolean validate = true;

    if (validate) {

      if (!saving) {
        Path path = this.getAutoSaveFilePath(deliverable, action.getCrpID());

        if (path.toFile().exists()) {
          action.addMissingField("draft");
        }
      }

      if (!(this.isValidString(deliverable.getDeliverableInfo(action.getActualPhase()).getTitle())
        && this.wordCount(deliverable.getDeliverableInfo(action.getActualPhase()).getTitle()) <= 15)) {
        action.addMessage(action.getText("project.deliverable.generalInformation.title"));
        action.getInvalidFields().put("input-deliverable.title", InvalidFieldsMessages.EMPTYFIELD);
      }
      if (deliverable.getDeliverableInfo(action.getActualPhase()).getDeliverableType() != null) {
        if (deliverable.getDeliverableInfo(action.getActualPhase()).getDeliverableType().getId() == null
          || deliverable.getDeliverableInfo(action.getActualPhase()).getDeliverableType().getId() == -1) {
          action.addMessage(action.getText("project.deliverable.generalInformation.subType"));
          action.getInvalidFields().put("input-deliverable.deliverableType.id", InvalidFieldsMessages.EMPTYFIELD);
        }
      } else {
        action.addMessage(action.getText("project.deliverable.generalInformation.subType"));
        action.getInvalidFields().put("input-deliverable.deliverableType.id", InvalidFieldsMessages.EMPTYFIELD);
        action.getInvalidFields().put("input-deliverable.deliverableType.deliverableType.id",
          InvalidFieldsMessages.EMPTYFIELD);
      }


      if (deliverable.getDeliverableInfo(action.getActualPhase()).getCrossCuttingGender() != null
        && deliverable.getDeliverableInfo(action.getActualPhase()).getCrossCuttingGender().booleanValue() == true) {

        if (deliverable.getGenderLevels() == null || deliverable.getGenderLevels().isEmpty()) {
          action.addMessage(action.getText("project.deliverable.generalInformation.genderLevels"));
          action.getInvalidFields().put("list-deliverable.genderLevels",
            action.getText(InvalidFieldsMessages.EMPTYLIST, new String[] {"Gender Levels"}));
        }
      }


      // Deliverable Dissemination
      if (deliverable.getDissemination() != null) {
        this.validateDissemination(deliverable.getDissemination(), saving, action);
      } else {
        action.addMessage(action.getText("project.deliverable.dissemination.v.dissemination"));
        action.getInvalidFields().put("input-deliverable.dissemination.isOpenAccess", InvalidFieldsMessages.EMPTYFIELD);
      }


      // Deliverable Publication Meta-data
      this.validatePublicationMetadata(deliverable, action);

      // Deliverable Licenses
      if (deliverable.getDeliverableInfo(action.getActualPhase()).getAdoptedLicense() != null) {
        this.validateLicense(deliverable, action);
      } else {
        action.addMessage(action.getText("project.deliverable.v.ALicense"));
        action.getInvalidFields().put("input-deliverable.adoptedLicense", InvalidFieldsMessages.EMPTYFIELD);
      }
      if (deliverable.getPrograms() != null) {
        if (deliverable.getPrograms().size() == 0) {
          if (deliverable.getFlagshipValue() == null || deliverable.getFlagshipValue().length() == 0) {
            action.addMessage(action.getText("projectDescription.flagships"));
            action.getInvalidFields().put("input-deliverable.flagshipValue", InvalidFieldsMessages.EMPTYFIELD);
          }

        }
      } else {
        if (deliverable.getFlagshipValue().length() == 0) {
          action.addMessage(action.getText("projectDescription.flagships"));
          action.getInvalidFields().put("input-deliverable.flagshipValue", InvalidFieldsMessages.EMPTYFIELD);
        }

      }

      if (deliverable.getRegions() != null) {
        if (deliverable.getRegions().size() == 0) {
          if (deliverable.getRegionsValue() == null || deliverable.getRegionsValue().length() == 0) {
            action.addMessage(action.getText("projectDescription.regions"));
            action.getInvalidFields().put("input-deliverable.regionsValue", InvalidFieldsMessages.EMPTYFIELD);
          }

        }
      } else {
        if (deliverable.getRegionsValue().length() == 0) {
          action.addMessage(action.getText("projectDescription.regions"));
          action.getInvalidFields().put("input-deliverable.regionsValue", InvalidFieldsMessages.EMPTYFIELD);
        }

      }

      if (deliverable.getLeaders() != null) {
        if (deliverable.getLeaders().size() == 0) {
          action.addMessage(action.getText("deliverable.leaders"));
          action.getInvalidFields().put("list-deliverable.leaders",
            action.getText(InvalidFieldsMessages.EMPTYLIST, new String[] {"Leaders"}));
        }
      } else {
        action.addMessage(action.getText("deliverable.leaders"));
        action.getInvalidFields().put("list-deliverable.leaders",
          action.getText(InvalidFieldsMessages.EMPTYLIST, new String[] {"Leaders"}));
      }

    }


    if (!action.getFieldErrors().isEmpty()) {
      action.addActionError(action.getText("saving.fields.required"));
    } else if (action.getValidationMessage().length() > 0) {
      action.addActionMessage(
        " " + action.getText("saving.missingFields", new String[] {action.getValidationMessage().toString()}));
    }
    if (action.isReportingActive()) {
      this.saveMissingFields(deliverable, APConstants.REPORTING, action.getReportingYear(),
        ProjectSectionStatusEnum.DELIVERABLES.getStatus(), action);
    } else {
      this.saveMissingFields(deliverable, APConstants.PLANNING, action.getPlanningYear(),
        ProjectSectionStatusEnum.DELIVERABLES.getStatus(), action);
    }
  }


  public void validateDissemination(DeliverableDissemination dissemination, boolean saving, BaseAction action) {

    if (dissemination.getIsOpenAccess() != null) {

      if (!dissemination.getIsOpenAccess().booleanValue()) {


        if (saving) {
          if (dissemination.getType() == null) {


            action.addMessage(action.getText("project.deliverable.dissemination.v.openAccessRestriction"));
            action.getInvalidFields().put("input-deliverable.dissemination.type", InvalidFieldsMessages.EMPTYFIELD);
          } else {
            if (dissemination.getType().equals("restrictedUseAgreement")) {

              if (dissemination.getRestrictedAccessUntil() == null) {
                action.addMessage(action.getText("project.deliverable.dissemination.v.restrictedUseAgreement"));
                action.getInvalidFields().put("input-deliverable.dissemination.restrictedAccessUntil",
                  InvalidFieldsMessages.EMPTYFIELD);
              }

            }

            if (dissemination.getType().equals("effectiveDateRestriction")) {

              if (dissemination.getRestrictedEmbargoed() == null) {
                action.addMessage(action.getText("project.deliverable.dissemination.v.restrictedEmbargoed"));
                action.getInvalidFields().put("input-deliverable.dissemination.restrictedEmbargoed",
                  InvalidFieldsMessages.EMPTYFIELD);
              }

            }
          }
        } else {
          boolean hasOne = false;
          if (dissemination.getIntellectualProperty() != null
            && dissemination.getIntellectualProperty().booleanValue()) {
            hasOne = true;
          }
          if (dissemination.getLimitedExclusivity() != null && dissemination.getLimitedExclusivity().booleanValue()) {
            hasOne = true;
          }
          if (dissemination.getRestrictedUseAgreement() != null
            && dissemination.getRestrictedUseAgreement().booleanValue()) {
            hasOne = true;
            if (dissemination.getRestrictedAccessUntil() == null) {
              action.addMessage(action.getText("project.deliverable.dissemination.v.restrictedUseAgreement"));
              action.getInvalidFields().put("input-deliverable.dissemination.restrictedAccessUntil",
                InvalidFieldsMessages.EMPTYFIELD);
            }
          }
          if (dissemination.getEffectiveDateRestriction() != null
            && dissemination.getEffectiveDateRestriction().booleanValue()) {
            hasOne = true;
            if (dissemination.getRestrictedEmbargoed() == null) {
              action.addMessage(action.getText("project.deliverable.dissemination.v.restrictedEmbargoed"));
              action.getInvalidFields().put("input-deliverable.dissemination.restrictedEmbargoed",
                InvalidFieldsMessages.EMPTYFIELD);
            }
          }

          if (!hasOne) {
            action.addMessage(action.getText("project.deliverable.dissemination.v.openAccessRestriction"));
            action.getInvalidFields().put("input-deliverable.dissemination.type", InvalidFieldsMessages.EMPTYFIELD);
          }
        }


      }

    } else {
      action.addMessage(action.getText("project.deliverable.dissemination.v.isOpenAccess"));
      action.getInvalidFields().put("input-deliverable.dissemination.isOpenAccess", InvalidFieldsMessages.EMPTYFIELD);
    }


    if (dissemination.getAlreadyDisseminated() != null) {
      if (dissemination.getAlreadyDisseminated().booleanValue()) {
        if (dissemination.getDisseminationChannel() != null) {
          if (dissemination.getDisseminationChannel().equals("-1")) {
            action.addMessage(action.getText("project.deliverable.dissemination.v.DisseminationChanel"));
            action.getInvalidFields().put("input-deliverable.dissemination.disseminationChannel",
              InvalidFieldsMessages.EMPTYFIELD);
          } else {
            if (!(this.isValidString(dissemination.getDisseminationUrl())
              && this.wordCount(dissemination.getDisseminationUrl()) <= 100)) {
              action.addMessage(action.getText("project.deliverable.dissemination.v.ChanelURL"));
              action.getInvalidFields().put("input-deliverable.dissemination.disseminationUrl",
                InvalidFieldsMessages.EMPTYFIELD);
            }
          }
        } else {
          action.addMessage(action.getText("project.deliverable.dissemination.v.DisseminationChanel"));
          action.getInvalidFields().put("input-deliverable.dissemination.disseminationChannel",
            InvalidFieldsMessages.EMPTYFIELD);
        }
      }
    } else {
      action.addMessage(action.getText("project.deliverable.dissemination.v.alreadyDisseminated"));
      action.getInvalidFields().put("input-deliverable.dissemination.isOpenAccess", InvalidFieldsMessages.EMPTYFIELD);

    }
  }


  public void validateLicense(Deliverable deliverable, BaseAction action) {
    if (deliverable.getDeliverableInfo(action.getActualPhase()).getAdoptedLicense().booleanValue()) {
      if (deliverable.getDeliverableInfo(action.getActualPhase()).getLicense() != null) {
        if (deliverable.getDeliverableInfo(action.getActualPhase()).getLicense()
          .equals(LicensesTypeEnum.OTHER.getValue())) {
          if (deliverable.getDeliverableInfo(action.getActualPhase()).getOtherLicense() != null) {
            if (!(this.isValidString(deliverable.getDeliverableInfo(action.getActualPhase()).getOtherLicense())
              && this.wordCount(deliverable.getDeliverableInfo(action.getActualPhase()).getOtherLicense()) <= 100)) {
              action.addMessage(action.getText("project.deliverable.license.v.other"));
              action.getInvalidFields().put("input-deliverable.otherLicense", InvalidFieldsMessages.EMPTYFIELD);
            }

            if (deliverable.getDeliverableInfo(action.getActualPhase()).getAllowModifications() == null) {
              action.addMessage(action.getText("project.deliverable.license.v.allowModification"));
              action.getInvalidFields().put("input-deliverable.dissemination.allowModification",
                InvalidFieldsMessages.EMPTYFIELD);
            }
          }
        }
      } else {
        action.addMessage(action.getText("project.deliverable.v.license"));
        action.getInvalidFields().put("input-deliverable.license", InvalidFieldsMessages.EMPTYFIELD);
      }
    }
  }

  public void validateMetadata(List<DeliverableMetadataElement> elements, BaseAction action) {

    boolean description = false;


    for (DeliverableMetadataElement deliverableMetadataElement : elements) {
      if (deliverableMetadataElement != null) {
        if (deliverableMetadataElement.getMetadataElement().getId() != null) {
          switch (deliverableMetadataElement.getMetadataElement().getId()) {
            case 8:
              if ((this.isValidString(deliverableMetadataElement.getElementValue())
                && this.wordCount(deliverableMetadataElement.getElementValue()) <= 100)) {
                description = true;
              }
              break;

          }
        }
      }
    }
    if (!description) {
      action.addMessage(action.getText("project.deliverable.metadata.v.description"));
      action.getInvalidFields().put("input-deliverable.metadataElements[7].elementValue",
        InvalidFieldsMessages.EMPTYFIELD);
    }


  }

  public void validatePublicationMetadata(Deliverable deliverable, BaseAction action) {

    if (deliverable.getPublication() != null && deliverable.getPublication().getId() != null
      && deliverable.getPublication().getId().intValue() != -1) {

      DeliverablePublicationMetadata metadata = deliverable.getPublication();

      if (!(this.isValidString(metadata.getVolume()) && this.wordCount(metadata.getVolume()) <= 100)) {
        // action.addMessage(action.getText("project.deliverable.publication.v.volume"));
        // action.getInvalidFields().put("input-deliverable.publication.volume", InvalidFieldsMessages.EMPTYFIELD);
      }

      if (!(this.isValidString(metadata.getJournal()) && this.wordCount(metadata.getJournal()) <= 100)) {
        action.addMessage(action.getText("project.deliverable.publication.v.journal"));
        action.getInvalidFields().put("input-deliverable.publication.journal", InvalidFieldsMessages.EMPTYFIELD);
      }

      boolean indicators = false;

      if (metadata.getIsiPublication() != null) {
        if (metadata.getIsiPublication().booleanValue()) {
          indicators = true;
        }
      }

      if (metadata.getNasr() != null) {
        if (metadata.getNasr().booleanValue()) {
          indicators = true;
        }
      }

      if (metadata.getCoAuthor() != null) {
        if (metadata.getCoAuthor().booleanValue()) {
          indicators = true;
        }
      }

      if (!indicators) {
        action.addMessage(action.getText("project.deliverable.publication.v.indicators"));
        action.getInvalidFields().put("input-deliverable.publication.nasr", InvalidFieldsMessages.EMPTYFIELD);
      }

      if (metadata.getPublicationAcknowledge() == null) {
        action.addMessage(action.getText("project.deliverable.publication.v.allowPublication"));
        action.getInvalidFields().put("input-deliverable.publication.publicationAcknowledge",
          InvalidFieldsMessages.EMPTYFIELD);
      }

      // else {
      // action.addMessage(action.getText("project.deliverable.v.publication"));
      // action.getInvalidFields().put("input-deliverable.publication.nasr", InvalidFieldsMessages.EMPTYFIELD);
      // }
    }
  }
}

