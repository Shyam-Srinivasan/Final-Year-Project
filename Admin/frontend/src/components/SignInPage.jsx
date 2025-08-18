import React, {useState} from 'react'
import { Link , useNavigate} from 'react-router-dom';
import axios from "axios";
import {Button, Col, Container, Form, Row} from "react-bootstrap";
import {ToastContainer, toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';


const API_BASE = `http://${window.location.hostname}:8080`;

export const SignInPage = () => {
    const [form, setForm] = useState({
        name: '',
        // TODO: Other details should be added for validation
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({
           ...form,
           [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try{

            const res = await axios.get(
                `${API_BASE}/signIn`,
                {
                    params: {
                        college_name: form.name
                    }
                });
            if(res.status === 200){
                const college = res.data;
                localStorage.setItem(
                    'college',
                    JSON.stringify(
                        {
                            id: college.college_id,
                            name: college.name
                        }
                    )
                );
                toast.success('Signed In Successful!', {autoClose: 2000});
                setTimeout(() => navigate('/shops'), 1500);
                // navigate('/shops');
            } else {
                toast.error('No Organization Found!');
            }

        }catch (err){
            alert(err.response?.data);
            const message = err.response?.data || 'Error: Something went wrong. Please try again later.';
            toast.error(message, {autoClose: 2000});
        }
    };

    return(
        <Container fluid className="min-vh-100 d-flex align-items-center justify-content-center">
            <Row className="w-100 justify-content-center">
                <Col xs={12} sm={10} md={8} lg={6} xl={5}>
                    <h1 className="text-center mb-4 text-dark fs-6">SignIn to your Organization</h1>
                    <Form className='p-4 shadow rounded bg-white' onSubmit={handleSubmit}>
                        <Form.Group className="mb-3">
                          <Form.Label className='text-dark'>College Name</Form.Label>
                          <Form.Control name="name" type="text" value={form.name} onChange={handleChange} required />
                        </Form.Group>
                        <Button variant='primary' type='submit' className='w-100'>Submit</Button>
                        <p className='mt-3 text-dark'>
                            Haven't created your Organization yet? <Link to='/signUp'>Sign Up</Link>
                        </p>
                    </Form>
                </Col>
            </Row>
            <ToastContainer position="top-right"/>
        </Container>
    );
}