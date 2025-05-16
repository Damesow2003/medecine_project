import {Traitement} from "./traitement.model";

export class Salle {
    idSalle!: number;
    numeroSalle!: string;
    nomSalle!: string;
    status!:string;
    traitements!: Traitement[];
}