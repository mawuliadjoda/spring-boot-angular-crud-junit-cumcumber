
import { Injectable } from "@angular/core";
import { Selector } from "@ngxs/store";
import { StateContext } from "@ngxs/store";
import { Action } from "@ngxs/store";
import { State } from "@ngxs/store";
import { tap } from "rxjs";
import { Pharmacy } from "../models/pharmacy";
import { PharmacyService } from "../pharmacy.service";
import { AddPharmacy, DeletePharmacy, GetPharmacies, SetSeletedPharmacy, UpdatePharmacy } from "./pharmacy.action.ts";

export class PharmacyStateModel {
  pharmacies?: Pharmacy[];
  selectedPharmacy?: Pharmacy;
}


@State<PharmacyStateModel>( {
  name: 'pharmacies',
  defaults: {
    pharmacies: [],
    selectedPharmacy: undefined
  }
})

@Injectable({
  providedIn: 'root'
})
export class PharmacyState {
  constructor(private pharmacyService: PharmacyService) {}

  @Selector()
  static getPharmacies(state: PharmacyStateModel) {
    return state.pharmacies;
  }

  @Selector()
  static getSelectedPharmacy(state: PharmacyStateModel) {
    return state.selectedPharmacy;
  }

  @Action(GetPharmacies)
  getPharmacies({getState, setState}: StateContext<PharmacyStateModel>) {
    return this.pharmacyService.getAll().pipe(
      tap((result) => {
        const state = getState();
        debugger;
        setState({
          ...state,
          pharmacies: result,
        })
      })
    );
  }

  @Action(AddPharmacy)
  addPharmacy(
    { getState, patchState}: StateContext<PharmacyStateModel>,
    { payload }: AddPharmacy
  ) {
    return this.pharmacyService.add(payload).pipe(
      tap( (result) => {
        const state = getState();
        patchState(  {
          pharmacies: state?.pharmacies ? [...state.pharmacies, result] : [...[], result]
        })
      })
    );
  }

  updatePharmacy(
    { getState, setState}: StateContext<PharmacyStateModel>,
    { payload, id }: UpdatePharmacy
  ) {
    return this.pharmacyService.update(id, payload).pipe(
      tap( (result) => {
        const state = getState();
        const pharmacies = state?.pharmacies ?  [...state?.pharmacies] : [];
        const index  = pharmacies.findIndex( (item) => item.id === id);
        pharmacies[index] = result;
        setState( {
          ...state,
          pharmacies: pharmacies,
        });
      })
    );
  }

  @Action(DeletePharmacy)
  deletePharmacy(
    {getState, setState}: StateContext<PharmacyStateModel>,
    { id}: DeletePharmacy
  ) {
    return this.pharmacyService.delete(id).pipe(
      tap( () => {
        const state = getState();
        const filteredArray = state?.pharmacies?.filter( (item)=> item.id !== id);
        setState( {
          ...state,
          pharmacies: filteredArray,
        });
      })
    );
  }


  @Action(SetSeletedPharmacy)
  setSeletedPharmacy(
    { getState, setState}: StateContext<PharmacyStateModel>,
    { payload }: SetSeletedPharmacy
  ) {
    const state = getState();
    setState({
      ...state,
      selectedPharmacy: payload
    });
  }

}
