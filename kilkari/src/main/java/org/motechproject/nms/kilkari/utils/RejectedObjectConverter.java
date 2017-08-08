package org.motechproject.nms.kilkari.utils;

import org.motechproject.nms.kilkari.contract.AnmAshaRecord;
import org.motechproject.nms.kilkari.contract.ChildRecord;
import org.motechproject.nms.kilkari.contract.MotherRecord;
import org.motechproject.nms.kilkari.contract.RchAnmAshaRecord;
import org.motechproject.nms.kilkari.contract.RchChildRecord;
import org.motechproject.nms.kilkari.contract.RchMotherRecord;
import org.motechproject.nms.rejectionhandler.domain.ChildImportRejection;
import org.motechproject.nms.rejectionhandler.domain.FlwImportRejection;
import org.motechproject.nms.rejectionhandler.domain.MotherImportRejection;

import java.util.Map;

/**
 * Created by beehyv on 20/7/17.
 */
public final class RejectedObjectConverter {

    private RejectedObjectConverter() {
    }

    public static FlwImportRejection flwRejectionRch(RchAnmAshaRecord record, Boolean accepted, String rejectionReason, String action) {
        FlwImportRejection flwImportRejection = new FlwImportRejection();
        flwImportRejection.setStateId(record.getStateId());
        flwImportRejection.setDistrictId(record.getDistrictId());
        flwImportRejection.setDistrictName(record.getDistrictName());
        flwImportRejection.setMsisdn(record.getMobileNo());
        flwImportRejection.setGfName(record.getGfName());
        flwImportRejection.setType(record.getGfType());
        flwImportRejection.setGfStatus(record.getGfStatus());
        flwImportRejection.setExecDate(record.getExecDate());
        flwImportRejection.setSource("RCH-Import");
        flwImportRejection.setAccepted(accepted);
        flwImportRejection.setTalukaId(record.getTalukaId());
        flwImportRejection.setHealthBlockId(record.getHealthBlockId());
        flwImportRejection.setHealthBlockName(record.getHealthBlockName());
        flwImportRejection.setPhcId(record.getPhcId());
        flwImportRejection.setPhcName(record.getPhcName());
        flwImportRejection.setSubcentreId(record.getSubCentreId());
        flwImportRejection.setSubcentreName(record.getSubCentreName());
        flwImportRejection.setVillageId(record.getVillageId());
        flwImportRejection.setVillageName(record.getVillageName());
        flwImportRejection.setFlwId(record.getGfId());
        flwImportRejection.setRejectionReason(rejectionReason);
        flwImportRejection.setAction(action);

        return flwImportRejection;
    }

    public static FlwImportRejection flwRejectionMcts(AnmAshaRecord record, Boolean accepted, String rejectionReason, String action) {
        FlwImportRejection flwImportRejection = new FlwImportRejection();
        flwImportRejection.setStateId(record.getStateId());
        flwImportRejection.setDistrictId(record.getDistrictId());
        flwImportRejection.setDistrictName(record.getDistrictName());
        flwImportRejection.setTalukaId(record.getTalukaId());
        flwImportRejection.setHealthBlockId(record.getHealthBlockId());
        flwImportRejection.setHealthBlockName(record.getHealthBlockName());
        flwImportRejection.setPhcId(record.getPhcId());
        flwImportRejection.setPhcName(record.getPhcName());
        flwImportRejection.setSubcentreId(record.getSubCentreId());
        flwImportRejection.setSubcentreName(record.getSubCentreName());
        flwImportRejection.setVillageId(record.getVillageId());
        flwImportRejection.setVillageName(record.getVillageName());
        flwImportRejection.setFlwId(record.getId());
        flwImportRejection.setMsisdn(record.getContactNo());
        flwImportRejection.setGfName(record.getName());
        flwImportRejection.setType(record.getType());
        flwImportRejection.setGfStatus(record.getGfStatus());
        flwImportRejection.setRegDate(record.getRegDate());
        flwImportRejection.setSex(record.getSex());
        flwImportRejection.setSmsReply(record.getSmsReply());
        flwImportRejection.setAadharNo(record.getAadharNo());
        flwImportRejection.setCreatedOn(record.getCreatedOn());
        flwImportRejection.setUpdatedOn(record.getUpdatedOn());
        flwImportRejection.setBankId(record.getBankId());
        flwImportRejection.setBranchName(record.getBranchName());
        flwImportRejection.setIfscIdCode(record.getIfscIdCode());
        flwImportRejection.setBankName(record.getBankName());
        flwImportRejection.setAccountNumber(record.getAccNo());
        flwImportRejection.setAadharLinked(record.getIsAadharLinked());
        flwImportRejection.setVerifyDate(record.getVerifyDate());
        flwImportRejection.setVerifierName(record.getVerifierName());
        flwImportRejection.setVerifierId(record.getVerifierId());
        flwImportRejection.setCallAns(record.getCallAns());
        flwImportRejection.setPhoneNoCorrect(record.getIsPhoneNoCorrect());
        flwImportRejection.setNoCallReason(record.getNoCallReason());
        flwImportRejection.setNoPhoneReason(record.getNoPhoneReason());
        flwImportRejection.setVerifierRemarks(record.getVerifierRemarks());
        flwImportRejection.setGfAddress(record.getGfAddress());
        flwImportRejection.setHusbandName(record.getHusbandName());
        flwImportRejection.setSource("MCTS-Import");
        flwImportRejection.setAccepted(accepted);
        flwImportRejection.setRejectionReason(rejectionReason);
        flwImportRejection.setAction(action);

        return flwImportRejection;
    }

    public static ChildImportRejection childRejectionRch(RchChildRecord record, Boolean accepted, String rejectionReason, String action) {
        ChildImportRejection childImportRejection = new ChildImportRejection();
        childImportRejection.setSubcentreId(record.getSubCentreId());
        childImportRejection.setSubcentreName(record.getSubCentreName());
        childImportRejection.setVillageId(record.getVillageId());
        childImportRejection.setVillageName(record.getVillageName());
        childImportRejection.setName(record.getName());
        childImportRejection.setMobileNo(record.getMobileNo());
        childImportRejection.setStateId(record.getStateId());
        childImportRejection.setDistrictId(record.getDistrictId());
        childImportRejection.setDistrictName(record.getDistrictName());
        childImportRejection.setTalukaId(record.getTalukaId());
        childImportRejection.setTalukaName(record.getTalukaName());
        childImportRejection.setHealthBlockId(record.getHealthBlockId());
        childImportRejection.setHealthBlockName(record.getHealthBlockName());
        childImportRejection.setPhcId(record.getPhcId());
        childImportRejection.setPhcName(record.getPhcName());
        childImportRejection.setBirthDate(record.getBirthdate());
        childImportRejection.setRegistrationNo(record.getRegistrationNo());
        childImportRejection.setEntryType(record.getEntryType());
        childImportRejection.setIdNo(record.getMctsId());
        childImportRejection.setmCTSMotherIDNo(record.getMctsMotherIdNo());
        childImportRejection.setExecDate(record.getExecDate());
        childImportRejection.setSource("RCH-Import");
        childImportRejection.setAccepted(accepted);
        childImportRejection.setRejectionReason(rejectionReason);
        childImportRejection.setAction(action);

        return childImportRejection;
    }

    public static ChildImportRejection childRejectionMcts(ChildRecord record, Boolean accepted, String rejectionReason, String action) { //NOPMD NcssMethodCount
        ChildImportRejection childImportRejection = new ChildImportRejection();
        childImportRejection.setStateId(record.getStateID());
        childImportRejection.setDistrictId(record.getDistrictId());
        childImportRejection.setDistrictName(record.getDistrictName());
        childImportRejection.setTalukaId(record.getTalukaId());
        childImportRejection.setTalukaName(record.getTalukaName());
        childImportRejection.setHealthBlockId(record.getHealthBlockId());
        childImportRejection.setHealthBlockName(record.getHealthBlockName());
        childImportRejection.setPhcId(record.getPhcId());
        childImportRejection.setPhcName(record.getPhcName());
        childImportRejection.setSubcentreId(record.getSubCentreId());
        childImportRejection.setSubcentreName(record.getSubCentreName());
        childImportRejection.setVillageId(record.getVillageId());
        childImportRejection.setVillageName(record.getVillageName());
        childImportRejection.setYr(record.getYr());
        childImportRejection.setCityMaholla(record.getCityMaholla());
        childImportRejection.setgPVillage(record.getGpVillage());
        childImportRejection.setAddress(record.getAddress());
        childImportRejection.setIdNo(record.getIdNo());
        childImportRejection.setName(record.getName());
        childImportRejection.setMotherName(record.getMotherName());
        childImportRejection.setMotherId(record.getMotherId());
        childImportRejection.setPhoneNumberWhom(record.getPhoneNoOfWhom());
        childImportRejection.setWhomPhoneNumber(record.getWhomPhoneNo());
        childImportRejection.setBirthDate(record.getBirthdate());
        childImportRejection.setPlaceOfDelivery(record.getPlaceOfDelivery());
        childImportRejection.setBloodGroup(record.getBloodGroup());
        childImportRejection.setCaste(record.getCaste());
        childImportRejection.setSubcenterName1(record.getSubCentreName1());
        childImportRejection.setaNMName(record.getAnmName());
        childImportRejection.setaNMPhone(record.getAnmPhone());
        childImportRejection.setAshaName(record.getAshaName());
        childImportRejection.setAshaPhone(record.getAshaPhone());
        childImportRejection.setbCGDt(record.getBcgDt());
        childImportRejection.setoPV0Dt(record.getOpv0Dt());
        childImportRejection.setHepatitisB1Dt(record.getHepatitisB1Dt());
        childImportRejection.setdPT1Dt(record.getDpt1Dt());
        childImportRejection.setoPV1Dt(record.getOpv1Dt());
        childImportRejection.setHepatitisB2Dt(record.getHepatitisB2Dt());
        childImportRejection.setdPT2Dt(record.getdPT2Dt());
        childImportRejection.setoPV2Dt(record.getOpv2Dt());
        childImportRejection.setHepatitisB3Dt(record.getHepatitisB3Dt());
        childImportRejection.setdPT3Dt(record.getDpt3Dt());
        childImportRejection.setoPV3Dt(record.getOpv3Dt());
        childImportRejection.setHepatitisB4Dt(record.getHepatitisB4Dt());
        childImportRejection.setMeaslesDt(record.getMeaslesDt());
        childImportRejection.setVitADose1Dt(record.getVitADose1Dt());
        childImportRejection.setmRDt(record.getMrDt());
        childImportRejection.setdPTBoosterDt(record.getDptBoosterDt());
        childImportRejection.setoPVBoosterDt(record.getOpvBoosterDt());
        childImportRejection.setVitADose2Dt(record.getVitADose2Dt());
        childImportRejection.setVitADose3Dt(record.getVitADose3Dt());
        childImportRejection.setjEDt(record.getJeDt());
        childImportRejection.setVitADose9Dt(record.getVitADose9Dt());
        childImportRejection.setdT5Dt(record.getDt5Dt());
        childImportRejection.settT10Dt(record.getTt10Dt());
        childImportRejection.settT16Dt(record.getTt16Dt());
        childImportRejection.setcLDRegDATE(record.getCldRegDate());
        childImportRejection.setSex(record.getSex());
        childImportRejection.setVitADose5Dt(record.getVitADose5Dt());
        childImportRejection.setVitADose6Dt(record.getVitADose6Dt());
        childImportRejection.setVitADose7Dt(record.getVitADose7Dt());
        childImportRejection.setVitADose8Dt(record.getVitADose8Dt());
        childImportRejection.setLastUpdateDate(record.getLastUpdateDate());
        childImportRejection.setRemarks(record.getRemarks());
        childImportRejection.setaNMID(record.getAnmID());
        childImportRejection.setAshaID(record.getAshaID());
        childImportRejection.setCreatedBy(record.getCreatedBy());
        childImportRejection.setUpdatedBy(record.getUpdatedBy());
        childImportRejection.setMeasles2Dt(record.getMeasles2Dt());
        childImportRejection.setWeightOfChild(record.getWeightofChild());
        childImportRejection.setChildAadhaarNo(record.getChildAadhaarNo());
        childImportRejection.setChildEID(record.getChildEID());
        childImportRejection.setChildEIDTime(record.getChildEIDTime());
        childImportRejection.setFatherName(record.getFatherName());
        childImportRejection.setBirthCertificateNumber(record.getBirthCertificateNumber());
        childImportRejection.setEntryType(record.getEntryType());
        childImportRejection.setSource("MCTS-Import");
        childImportRejection.setAccepted(accepted);
        childImportRejection.setRejectionReason(rejectionReason);
        childImportRejection.setAction(action);


        return childImportRejection;
    }

    public static MotherImportRejection motherRejectionRch(RchMotherRecord record, Boolean accepted, String rejectionReason, String action) {
        MotherImportRejection motherImportRejection = new MotherImportRejection();
        motherImportRejection.setStateId(record.getStateId());
        motherImportRejection.setDistrictId(record.getDistrictId());
        motherImportRejection.setDistrictName(record.getDistrictName());
        motherImportRejection.setTalukaId(record.getTalukaId());
        motherImportRejection.setTalukaName(record.getTalukaName());
        motherImportRejection.setHealthBlockId(record.getHealthBlockId());
        motherImportRejection.setHealthBlockName(record.getHealthBlockName());
        motherImportRejection.setPhcId(record.getPhcId());
        motherImportRejection.setPhcName(record.getPhcName());
        motherImportRejection.setSubcentreId(record.getSubCentreId());
        motherImportRejection.setSubcentreName(record.getSubCentreName());
        motherImportRejection.setVillageId(record.getVillageId());
        motherImportRejection.setVillageName(record.getVillageName());
        motherImportRejection.setIdNo(record.getMctsIdNo());
        motherImportRejection.setRegistrationNo(record.getRegistrationNo());
        motherImportRejection.setCaseNo(record.getCaseNo());
        motherImportRejection.setName(record.getName());
        motherImportRejection.setMobileNo(record.getMobileNo());
        motherImportRejection.setLmpDate(record.getLmpDate());
        motherImportRejection.setBirthDate(record.getBirthDate());
        motherImportRejection.setAbortionType(record.getAbortionType());
        motherImportRejection.setDeliveryOutcomes(record.getDeliveryOutcomes());
        motherImportRejection.setEntryType(record.getEntryType());
        motherImportRejection.setExecDate(record.getExecDate());
        motherImportRejection.setSource("RCH-Import");
        motherImportRejection.setAccepted(accepted);
        motherImportRejection.setRejectionReason(rejectionReason);
        motherImportRejection.setAction(action);


        return motherImportRejection;
    }

    public static MotherImportRejection motherRejectionMcts(MotherRecord record, Boolean accepted, String rejectionReason, String action) { //NOPMD NcssMethodCount
        MotherImportRejection motherImportRejection = new MotherImportRejection();
        motherImportRejection.setStateId(record.getStateId());
        motherImportRejection.setDistrictId(record.getDistrictId());
        motherImportRejection.setDistrictName(record.getDistrictName());
        motherImportRejection.setTalukaId(record.getTalukaId());
        motherImportRejection.setTalukaName(record.getTalukaName());
        motherImportRejection.setHealthBlockId(record.getHealthBlockId());
        motherImportRejection.setHealthBlockName(record.getHealthBlockName());
        motherImportRejection.setPhcId(record.getPhcid());
        motherImportRejection.setPhcName(record.getPhcName());
        motherImportRejection.setSubcentreId(record.getSubCentreid());
        motherImportRejection.setSubcentreName(record.getSubCentreName());
        motherImportRejection.setVillageId(record.getVillageId());
        motherImportRejection.setVillageName(record.getVillageName());
        motherImportRejection.setYr(record.getYr());
        motherImportRejection.setgPVillage(record.getGpVillage());
        motherImportRejection.setAddress(record.getAddress());
        motherImportRejection.setIdNo(record.getIdNo());
        motherImportRejection.setName(record.getName());
        motherImportRejection.setHusbandName(record.getHusbandName());
        motherImportRejection.setPhoneNumberWhom(record.getPhoneNoOfWhom());
        motherImportRejection.setWhomPhoneNumber(record.getWhomPhoneNo());
        motherImportRejection.setBirthDate(record.getBirthdate());
        motherImportRejection.setjSYBeneficiary(record.getJsyBeneficiary());
        motherImportRejection.setCaste(record.getCaste());
        motherImportRejection.setSubcenterName1(record.getSubCentreName1());
        motherImportRejection.setaNMName(record.getAnmName());
        motherImportRejection.setaNMPhone(record.getAnmPhone());
        motherImportRejection.setAshaName(record.getAshaName());
        motherImportRejection.setAshaPhone(record.getAshaPhone());
        motherImportRejection.setDeliveryLnkFacility(record.getDeliveryLnkFacility());
        motherImportRejection.setFacilityName(record.getFacilityName());
        motherImportRejection.setLmpDate(record.getLmpDate());
        motherImportRejection.setaNC1Date(record.getAnc1Date());
        motherImportRejection.setaNC2Date(record.getAnc2Date());
        motherImportRejection.setaNC3Date(record.getAnc3Date());
        motherImportRejection.setaNC4Date(record.getAnc4Date());
        motherImportRejection.settT1Date(record.getTt1Date());
        motherImportRejection.settT2Date(record.getTt2Date());
        motherImportRejection.settTBoosterDate(record.getTtBoosterDate());
        motherImportRejection.setiFA100GivenDate(record.getIfA100GivenDate());
        motherImportRejection.setAnemia(record.getAnemia());
        motherImportRejection.setaNCComplication(record.getAncComplication());
        motherImportRejection.setrTISTI(record.getRtiSTI());
        motherImportRejection.setDlyDate(record.getDlyDate());
        motherImportRejection.setDlyPlaceHomeType(record.getDlyPlaceHomeType());
        motherImportRejection.setDlyPlacePublic(record.getDlyPlacePublic());
        motherImportRejection.setDlyPlacePrivate(record.getDlyPlacePrivate());
        motherImportRejection.setDlyType(record.getDlyType());
        motherImportRejection.setDlyComplication(record.getDlyComplication());
        motherImportRejection.setDischargeDate(record.getDischargeDate());
        motherImportRejection.setjSYPaidDate(record.getJsyPaidDate());
        motherImportRejection.setAbortion(record.getAbortion());
        motherImportRejection.setpNCHomeVisit(record.getPncHomeVisit());
        motherImportRejection.setpNCComplication(record.getPncComplication());
        motherImportRejection.setpPCMethod(record.getPpcMethod());
        motherImportRejection.setpNCCheckup(record.getPncCheckup());
        motherImportRejection.setOutcomeNos(record.getOutcomeNos());
        motherImportRejection.setChild1Name(record.getChild1Name());
        motherImportRejection.setChild1Sex(record.getChild1Sex());
        motherImportRejection.setChild1Wt(record.getChild1Wt());
        motherImportRejection.setChild1Brestfeeding(record.getChild1Brestfeeding());
        motherImportRejection.setChild2Name(record.getChild2Name());
        motherImportRejection.setChild2Sex(record.getChild2Sex());
        motherImportRejection.setChild2Wt(record.getChild2Wt());
        motherImportRejection.setChild2Brestfeeding(record.getChild2Brestfeeding());
        motherImportRejection.setChild3Name(record.getChild3Name());
        motherImportRejection.setChild3Sex(record.getChild3Sex());
        motherImportRejection.setChild3Wt(record.getChild3Wt());
        motherImportRejection.setChild3Brestfeeding(record.getChild3Brestfeeding());
        motherImportRejection.setChild4Name(record.getChild4Name());
        motherImportRejection.setChild4Sex(record.getChild4Sex());
        motherImportRejection.setChild4Wt(record.getChild4Wt());
        motherImportRejection.setChild4Brestfeeding(record.getChild4Brestfeeding());
        motherImportRejection.setAge(record.getAge());
        motherImportRejection.setmTHRREGDATE(record.getMthrRegDate());
        motherImportRejection.setLastUpdateDate(record.getLastUpdateDate());
        motherImportRejection.setRemarks(record.getRemarks());
        motherImportRejection.setaNMID(record.getAnmID());
        motherImportRejection.setaSHAID(record.getAshaID());
        motherImportRejection.setCallAns(record.getCallAns());
        motherImportRejection.setNoCallReason(record.getNoCallReason());
        motherImportRejection.setNoPhoneReason(record.getNoPhoneReason());
        motherImportRejection.setCreatedBy(record.getCreatedBy());
        motherImportRejection.setUpdatedBy(record.getUpdatedBy());
        motherImportRejection.setAadharNo(record.getAadharNo());
        motherImportRejection.setbPLAPL(record.getBplAPL());
        motherImportRejection.seteID(record.geteId());
        motherImportRejection.seteIDTime(record.geteIdTime());
        motherImportRejection.setEntryType(record.getEntryType());
        motherImportRejection.setSource("MCTS-Import");
        motherImportRejection.setAccepted(accepted);
        motherImportRejection.setRejectionReason(rejectionReason);
        motherImportRejection.setAction(action);


        return motherImportRejection;
    }

    public static MotherRecord convertMapToMother(Map<String, Object> record) {
        MotherRecord motherRecord = new MotherRecord();
        motherRecord.setStateId((Long) record.get(KilkariConstants.STATE_ID));
        motherRecord.setDistrictId((Long) record.get(KilkariConstants.DISTRICT_ID));
        motherRecord.setDistrictName(record.get(KilkariConstants.DISTRICT_NAME).toString());
        motherRecord.setTalukaId(record.get(KilkariConstants.TALUKA_ID).toString());
        motherRecord.setTalukaName(record.get(KilkariConstants.TALUKA_NAME).toString());
        motherRecord.setHealthBlockId((Long) record.get(KilkariConstants.HEALTH_BLOCK_ID));
        motherRecord.setHealthBlockName(record.get(KilkariConstants.HEALTH_BLOCK_NAME).toString());
        motherRecord.setPhcid((Long) record.get(KilkariConstants.PHC_ID));
        motherRecord.setPhcName(record.get(KilkariConstants.PHC_NAME).toString());
        motherRecord.setSubCentreid((Long) record.get(KilkariConstants.SUB_CENTRE_ID));
        motherRecord.setSubCentreName(record.get(KilkariConstants.SUB_CENTRE_NAME).toString());
        motherRecord.setVillageId((Long) record.get(KilkariConstants.CENSUS_VILLAGE_ID));
        motherRecord.setVillageName(record.get(KilkariConstants.VILLAGE_NAME).toString());
        motherRecord.setLastUpdateDate(record.get(KilkariConstants.LAST_UPDATE_DATE).toString());

        motherRecord.setIdNo(record.get(KilkariConstants.BENEFICIARY_ID).toString());
        motherRecord.setName(record.get(KilkariConstants.BENEFICIARY_NAME).toString());
        motherRecord.setWhomPhoneNo(record.get(KilkariConstants.MSISDN).toString());
        motherRecord.setLmpDate(record.get(KilkariConstants.LMP).toString());
        motherRecord.setBirthdate(record.get(KilkariConstants.MOTHER_DOB).toString());
        motherRecord.setAbortion(record.get(KilkariConstants.ABORTION).toString());
        motherRecord.setOutcomeNos((Boolean) record.get(KilkariConstants.STILLBIRTH) ? 1 : 0);
        motherRecord.setEntryType((Boolean) record.get(KilkariConstants.DEATH) ? 1 : 0);
        return motherRecord;
    }

    public static RchMotherRecord convertMapToRchMother(Map<String, Object> record) {

        RchMotherRecord rchMotherRecord = new RchMotherRecord();
        rchMotherRecord.setStateId((Long) record.get(KilkariConstants.STATE_ID));
        rchMotherRecord.setDistrictId((Long) record.get(KilkariConstants.DISTRICT_ID));
        rchMotherRecord.setDistrictName(record.get(KilkariConstants.DISTRICT_NAME).toString());
        rchMotherRecord.setTalukaId(record.get(KilkariConstants.TALUKA_ID).toString());
        rchMotherRecord.setTalukaName(record.get(KilkariConstants.TALUKA_NAME).toString());
        rchMotherRecord.setHealthBlockId((Long) record.get(KilkariConstants.HEALTH_BLOCK_ID));
        rchMotherRecord.setHealthBlockName(record.get(KilkariConstants.HEALTH_BLOCK_NAME).toString());
        rchMotherRecord.setPhcId((Long) record.get(KilkariConstants.PHC_ID));
        rchMotherRecord.setPhcName(record.get(KilkariConstants.PHC_NAME).toString());
        rchMotherRecord.setSubCentreId((Long) record.get(KilkariConstants.SUB_CENTRE_ID));
        rchMotherRecord.setSubCentreName(record.get(KilkariConstants.SUB_CENTRE_NAME).toString());
        rchMotherRecord.setVillageId((Long) record.get(KilkariConstants.CENSUS_VILLAGE_ID));
        rchMotherRecord.setVillageName(record.get(KilkariConstants.VILLAGE_NAME).toString());

        rchMotherRecord.setMctsIdNo(record.get(KilkariConstants.MCTS_ID).toString());
        rchMotherRecord.setRegistrationNo(record.get(KilkariConstants.RCH_ID).toString());
        rchMotherRecord.setName(record.get(KilkariConstants.BENEFICIARY_NAME).toString());
        rchMotherRecord.setMobileNo(record.get(KilkariConstants.MOBILE_NO).toString());
        rchMotherRecord.setLmpDate(record.get(KilkariConstants.LMP).toString());
        rchMotherRecord.setBirthDate(record.get(KilkariConstants.MOTHER_DOB).toString());
        rchMotherRecord.setAbortionType(record.get(KilkariConstants.ABORTION_TYPE) == null ? null : record.get(KilkariConstants.ABORTION_TYPE).toString());
        rchMotherRecord.setDeliveryOutcomes(record.get(KilkariConstants.DELIVERY_OUTCOMES) == null ? null : record.get(KilkariConstants.DELIVERY_OUTCOMES).toString());
        rchMotherRecord.setEntryType((Boolean) record.get(KilkariConstants.DEATH) ? 1 : 0);
        rchMotherRecord.setExecDate(record.get(KilkariConstants.EXECUTION_DATE).toString());
        rchMotherRecord.setCaseNo((Long) record.get(KilkariConstants.CASE_NO));
        return rchMotherRecord;
    }

    public static ChildRecord convertMapToChild(Map<String, Object> record) {
        ChildRecord childRecord = new ChildRecord();
        childRecord.setStateID((Long) record.get(KilkariConstants.STATE_ID));
        childRecord.setDistrictId((Long) record.get(KilkariConstants.DISTRICT_ID));
        childRecord.setDistrictName(record.get(KilkariConstants.DISTRICT_NAME).toString());
        childRecord.setTalukaId(record.get(KilkariConstants.TALUKA_ID).toString());
        childRecord.setTalukaName(record.get(KilkariConstants.TALUKA_NAME).toString());
        childRecord.setHealthBlockId((Long) record.get(KilkariConstants.HEALTH_BLOCK_ID));
        childRecord.setHealthBlockName(record.get(KilkariConstants.HEALTH_BLOCK_NAME).toString());
        childRecord.setPhcId((Long) record.get(KilkariConstants.PHC_ID));
        childRecord.setPhcName(record.get(KilkariConstants.PHC_NAME).toString());
        childRecord.setSubCentreId((Long) record.get(KilkariConstants.SUB_CENTRE_ID));
        childRecord.setSubCentreName(record.get(KilkariConstants.SUB_CENTRE_NAME).toString());
        childRecord.setVillageId((Long) record.get(KilkariConstants.CENSUS_VILLAGE_ID));
        childRecord.setVillageName(record.get(KilkariConstants.VILLAGE_NAME).toString());
        childRecord.setLastUpdateDate(record.get(KilkariConstants.LAST_UPDATE_DATE).toString());

        childRecord.setName(record.get(KilkariConstants.BENEFICIARY_NAME).toString());

        childRecord.setWhomPhoneNo(record.get(KilkariConstants.MSISDN).toString());
        childRecord.setBirthdate(record.get(KilkariConstants.DOB).toString());

        childRecord.setIdNo(record.get(KilkariConstants.BENEFICIARY_ID).toString());
        childRecord.setMotherId(record.get(KilkariConstants.MOTHER_ID).toString());
        childRecord.setEntryType((Boolean) record.get(KilkariConstants.DEATH) ? 1 : 0);
        return childRecord;
    }

    public static RchChildRecord convertMapToRchChild(Map<String, Object> record) {
        RchChildRecord rchChildRecord = new RchChildRecord();
        rchChildRecord.setStateId((Long) record.get(KilkariConstants.STATE_ID));
        rchChildRecord.setDistrictId((Long) record.get(KilkariConstants.DISTRICT_ID));
        rchChildRecord.setDistrictName(record.get(KilkariConstants.DISTRICT_NAME).toString());
        rchChildRecord.setTalukaId(record.get(KilkariConstants.TALUKA_ID).toString());
        rchChildRecord.setTalukaId(record.get(KilkariConstants.TALUKA_NAME).toString());
        rchChildRecord.setHealthBlockId((Long) record.get(KilkariConstants.HEALTH_BLOCK_ID));
        rchChildRecord.setHealthBlockName(record.get(KilkariConstants.HEALTH_BLOCK_NAME).toString());
        rchChildRecord.setPhcId((Long) record.get(KilkariConstants.PHC_ID));
        rchChildRecord.setPhcName(record.get(KilkariConstants.PHC_NAME).toString());
        rchChildRecord.setSubCentreId((Long) record.get(KilkariConstants.SUB_CENTRE_ID));
        rchChildRecord.setSubCentreName(record.get(KilkariConstants.SUB_CENTRE_NAME).toString());
        rchChildRecord.setVillageId((Long) record.get(KilkariConstants.CENSUS_VILLAGE_ID));
        rchChildRecord.setVillageName(record.get(KilkariConstants.VILLAGE_NAME).toString());

        rchChildRecord.setName(record.get(KilkariConstants.BENEFICIARY_NAME).toString());

        rchChildRecord.setMobileNo(record.get(KilkariConstants.MOBILE_NO).toString());
        rchChildRecord.setBirthdate(record.get(KilkariConstants.DOB).toString());

        rchChildRecord.setMctsId(record.get(KilkariConstants.MCTS_ID).toString());
        rchChildRecord.setMctsMotherIdNo(record.get(KilkariConstants.MCTS_MOTHER_ID).toString());
        rchChildRecord.setRegistrationNo(record.get(KilkariConstants.RCH_ID).toString());
        rchChildRecord.setMotherRegistrationNo(record.get(KilkariConstants.RCH_MOTHER_ID).toString());
        rchChildRecord.setEntryType((Boolean) record.get(KilkariConstants.DEATH) ? 1 : 0);
        rchChildRecord.setExecDate(record.get(KilkariConstants.EXECUTION_DATE).toString());

        return rchChildRecord;
    }

}
