import {RendezvousAPI} from "../../rendezvous/modeles/rendezvousAPI.model";

export class TraitementAPI{
    idTraitement!: number;
    nom!: string;
    idPatient!: number;
    rendezvous?: RendezvousAPI | null ;
}