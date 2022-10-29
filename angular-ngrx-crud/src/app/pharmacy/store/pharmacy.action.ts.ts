import { Pharmacy } from "../models/pharmacy";


export class GetPharmacies {
  static readonly type = '[Pharmacy] Get';
  constructor() {}
}

export class AddPharmacy {
  static readonly type = '[Pharmacy] Add';
  constructor(public payload: Pharmacy) {}
}

export class UpdatePharmacy {
  static readonly type = '[Pharmacy] Update';
  constructor(public id: number, public payload: Pharmacy) {}
}

export class DeletePharmacy {
  static readonly type = '[Pharmacy] Delete';
  constructor(public id: number) {}
}

export class SetSeletedPharmacy {
  static readonly type = '[Pharmacy] Set';
  constructor(public payload: Pharmacy) {}
}
