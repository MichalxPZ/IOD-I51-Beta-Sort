import React, {useEffect} from 'react';
import './App.css';

class Sort extends React.Component {
    constructor(props) {

        super(props);
        this.state = {
            data: [],
            property: '',
            sortingOrder: "ASCENDING",
            iterationNumber: null,
            algorithm: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({
            data: event.target.data,
            property: event.target.property,
            sortingOrder: event.target.sortingOrder,
            iterationNumber:event.target.iterationNumber,
            algorithm: event.target.algorithm
        });
    }

    handleSubmit = async () => {

        try {
            const response = await fetch('http://localhost:8080/api/sort/onedimension/int', {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                },
                body: this.state
            });

            if (!response.ok) {
                throw new Error(`Error! status: ${response.status}`);
            }

            const result = await response.json();

            console.log('result is: ', JSON.stringify(result, null, 4));

            this.setState(result);
        } catch (err) {
        }
    };

    render() {
        return (
            <div className= "form-box">
                <h5>Sort your data</h5>
                <form onSubmit={this.handleSubmit}>
                    <label className= "data-label">
                        Data:
                        <input type="text" value={this.state.data} onChange={this.handleChange} />
                    </label>
                    <br/>
                    <label>
                        Order:
                        <select value={this.state.sortingOrder} onChange={this.handleChange}>
                            <option value="ASCENDING">Ascending</option>
                            <option value="DESCENDING">Descending</option>
                        </select>
                    </label>
                    <br/>
                    <label>
                        Number of iterations:
                        <input type="text" value={this.state.iterationNumber} onChange={this.handleChange} />
                    </label>
                    <br/>
                    <label>
                        Algorithm:
                        <select value={this.state.algorithm} onChange={this.handleChange}>
                            <option value="SORT">Alg1</option>
                            <option value="SORT">Alg2</option>
                        </select>
                    </label>
                    <br/>
                    <label>
                        Property to sort (in case of multidimensional data source):
                        <input type="text" value={this.state.property} onChange={this.handleChange} />
                    </label>
                    <br/>

                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}
export default Sort;