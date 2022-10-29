import { AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { Pharmacy } from './models/pharmacy';
import { Observable } from 'rxjs';
import { Select, Store } from '@ngxs/store';
import { GetPharmacies } from './store/pharmacy.action.ts';
import { MatPaginator } from '@angular/material/paginator';
import { PharmacyState } from './store/pharmacy.state.ts';

@Component({
  selector: 'app-pharmacy',
  templateUrl: './pharmacy.component.html',
  styleUrls: ['./pharmacy.component.css']
})
export class PharmacyComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['id', 'name', 'address', 'lat', 'lng'];
  dataSource: MatTableDataSource<Pharmacy> = new MatTableDataSource<Pharmacy>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;



  readonly searchControl: FormControl = new FormControl('');

  @Select(PharmacyState.getPharmacies)
  private pharmacyState$: Observable<Pharmacy[]>;
  private selectedPharmacy: Pharmacy;

  constructor(private store: Store) { }

  ngOnInit() {
    this.store.dispatch(new GetPharmacies());
  }

  ngAfterViewInit(): void {
    this.loadDataSource();
    this.searchControl.valueChanges.subscribe((searchText) => this.dataSource.filter = searchText);
  }

  private loadDataSource() {
    this.pharmacyState$.subscribe( data => {
      this.dataSource.data = data;
    });
  }
}
