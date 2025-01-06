package dades.tipusMembre;

public enum SiglasTitulacio {
    EB,   // Enginyeria Biomèdica
    ESST,   // Enginyeria de Sistemes i Serveis de Telecomunicacions
    EEIA,   // Enginyeria Electrònica Industrial i Automàtica
    EE,   // Enginyeria Elèctrica
    EI,   // Enginyeria Informàtica
    EMF,  // Enginyeria Matemàtica i Física
    TDAWM;   // Tècniques de Desenvolupament d'Aplicacions Web i Mòbils

    @Override
    public String toString() {
        switch (this) {
            case EB: return "Enginyeria Biomèdica";
            case ESST: return "Enginyeria de Sistemes i Serveis de Telecomunicacions";
            case EEIA: return "Enginyeria Electrònica Industrial i Automàtica";
            case EE: return "Enginyeria Elèctrica";
            case EI: return "Enginyeria Informàtica";
            case EMF: return "Enginyeria Matemàtica i Física";
            case TDAWM: return "Tècniques de Desenvolupament d'Aplicacions Web i Mòbils";
            default: return super.toString();
        }
    }
}
