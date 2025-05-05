import {CabinetModel} from "../../cabinets/modeles/cabinet.model";

export class Medecin {
    id!: number
    prenom!: string
    nom!: string
    email!: string
    password!: string
    telephone!: string
    adresse!: string
    role!: string
    confirmEmail!: string
    confirmPassword!: string
    contactPreference!: string
    username!: string
    dateDeNaissance!: string
    matricule!: number
    specialite!: string
    traitementList!: any[]
    cabinetMedicals!:CabinetModel[];
}