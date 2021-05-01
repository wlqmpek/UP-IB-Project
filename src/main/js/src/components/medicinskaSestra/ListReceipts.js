import React, { Component } from 'react';
import ClinicsService from '../../services/ClinicsService';
import ReceiptService from '../../services/ReceiptsService';
import PreglediService from '../../services/PreglediService';

import '../../App.css'

class ListReceiptsComponent extends Component {

	constructor(props) {
		super(props)

		this.state = {
			idMedSestre: this.props.match.params.idMedSestre,
			idKlinike: this.props.match.params.idKlinike,
			receipts: [],
			pregledi: []
		}
		this.editReceipt = this.editReceipt.bind(this);
		this.editPregled = this.editPregled.bind(this);
	}


	editReceipt(id, recept) {
		ReceiptService.editReceipt(id, recept).then(response => {
			this.state.receipts.forEach(recept => {
				if (recept.idRecepta == id) {
					recept.overen = 1;
				}
			});
			this.setState({
				receipts: this.state.receipts.filter(recept => recept.overen == 0)
			})
		});	
	}

	editPregled(id, idRecepta) {
		var pregled = this.state.pregledi.find(pregled => pregled.idPregleda == id);
		pregled.idMedicinskeSestre = this.state.idMedSestre;
		PreglediService.editPregled(id, pregled);
	}


	componentDidMount() {

		// RADI PREKO PETLJE, ALI FILTER() IZ NEKOG RAZLOGA NE RADI
		PreglediService.getPregledi().then((response) => {
			var allPregledi = response.data;
			var i;
			for (i = 0; i < allPregledi.length; i++) {
				if (allPregledi[i].idKlinike == this.state.idKlinike) {
					this.state.pregledi.push(allPregledi[i]);
				}
			}
		});

		console.log(this.state.pregledi);

		// CEKA SE DEBAGOVANJE, POREDJENJE NIJE DOBRO (?) IZ NEKOG RAZLOGA
		// update: problemi se javljali jer nisam koristio setState i this.state
		// kako i gdje treba...
		ReceiptService.getReceipts().then(response => {
			var allReceipts = response.data;
			var i;
			var receipts = [];
			for (i = 0; i < allReceipts.length; i++) {
				var j;
				for (j = 0; j < this.state.pregledi.length; j++) {
					if (allReceipts[i].idPregleda == this.state.pregledi[j].idPregleda) {
						if (allReceipts[i].overen == 0) {
							receipts.push(allReceipts[i])	
							break;
						}
					}
				}
			}
			this.setState({
				receipts: receipts.filter(recept => recept.overen == 0)
			})
		});
		

		
		

	}

	render() {

		return (

			<div style={{ margin: "auto", width: '50%' }}>
				<h2 style={{ margin: "10px", textDecoration: "underline" }} className="text-center">Recepti za overu</h2>
				<div className="row">

					<table className="table table-striped table bordered">

						<thead>
							<tr>
								<th> ID recepta </th>
								<th> Opis recepta</th>
								<th> Status </th>
								<th></th>
							</tr>
						</thead>

						<tbody>
							{
								this.state.receipts.map(
									receipt =>
										<tr id="lista" key={receipt.idRecepta}>
											<td>{receipt.idRecepta}</td>
											<td>{receipt.opisRecepta}</td>
											<td>{receipt.overen == 0 ? 'NEOVEREN' : 'OVEREN'}</td>
											<td style={{ width: '15%' }}>
												<button style={{ marginLeft: "10px" }} onClick={() => {
													this.editReceipt(receipt.idRecepta, receipt)
													this.editPregled(receipt.idPregleda, receipt.idRecepta)
												}} className="btn btn-default">Overi</button>
											</td>
										</tr>
								)
							}
						</tbody>
					</table>
				</div>
			</div>

		)

	}
}

export default ListReceiptsComponent;