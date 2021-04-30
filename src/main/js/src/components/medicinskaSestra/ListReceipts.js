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
	}


	editReceipt(id) {
		this.props.history.push(`/medicinskaSestra/${this.state.idMedSestre}/overaRecepta/${id}`);
	}


	componentDidMount() {

		PreglediService.getPregledi().then((response) => {
			this.setState({
				pregledi: response.data.filter(pregled => pregled.idKlinike == this.state.idKlinike)
			});
		});
		console.log(this.state.pregledi);
		ReceiptService.getReceipts().then(response => {
			var allReceipts = response.data;
			var i;
			for (i = 0; i < allReceipts.length; i++) {
				var j;
				for (j = 0; j < this.state.pregledi.length; j++) {
					if (allReceipts[i].idPregleda === this.state.pregledi[j].idPregleda) {
						this.state.receipts.push(allReceipts[i]);
						break;
					}
				}
			}
		});

	}

	render() {

		return (

			<div style={{ margin: "auto", width: '70%' }}>
				<h2 style={{ margin: "10px", textDecoration: "underline" }} className="text-center">Recepti za overu</h2>
				<div className="row">

					<table className="table table-striped table bordered">

						<thead>
							<tr>
								<th> ID recepta </th>
								<th> Opis recepta</th>
								<th> Overen </th>
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
											<td>{receipt.overen}</td>
											<td style={{ width: '35%' }}>
												<button style={{ marginLeft: "10px" }} onClick={() => this.editReceipt(receipt.idRecepta)} className="btn btn-default">Overi</button>
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