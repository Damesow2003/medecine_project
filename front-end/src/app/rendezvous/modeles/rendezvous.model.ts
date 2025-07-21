import {CabinetSimple} from "../../cabinets/modeles/cabinet-simple.model";
import {PatientSimple} from "../../patient/modeles/patientSimple.model";

export class RendezvousModel {
  id!: number;
  dateRv!: string;
  heureRv!: string;
  duree!: number;
/*  notifications!: Notification[];
  prescriptions!: Prescription[];*/

  patient!:PatientSimple
  cabinet!:CabinetSimple;
}
