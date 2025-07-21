import {CabinetModel} from "../../cabinets/modeles/cabinet.model";
import {CabinetSimple} from "../../cabinets/modeles/cabinet-simple.model";

export class Medecin {
    matricule!: number
    specialite!: string
    prenom!: string
    nom!: string
    email!: string
    telephone!: string
    adresse!: string;
    dateDeNaissance!:string;
    cabinets!: CabinetSimple[]
    //traitements: Traitement[]
}