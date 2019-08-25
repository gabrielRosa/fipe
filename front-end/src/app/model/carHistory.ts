export class CarHistory {
  name: string;
  ano_modelo: string;
  valueChanged: string;
  percentageChanged: string;
  baseYear: string;
  preco: string;

  constructor(name: string, ano_modelo: string, valueChanged: string, baseYear: string, percentageChanged: string, preco: string) {
    this.name = name;
    this.ano_modelo = ano_modelo;
    this.valueChanged = valueChanged;
    this.baseYear = baseYear;
    this.percentageChanged = percentageChanged;
    this.preco = preco;
  }

}
