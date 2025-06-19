import {Traitement} from "./traitement.model";
import {CabinetModel} from "../../cabinets/modeles/cabinet.model";
import {TraitementAPI} from "./traitementAPI.model";

export class Salle {
    idSalle!: number;
    numeroSalle!: string;
    nomSalle!: string;
    status!:string;
    cabinet!:CabinetModel;
    traitements!:TraitementAPI[]
}