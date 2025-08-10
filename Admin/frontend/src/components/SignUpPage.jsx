import React, { useState } from 'react';
import axios from 'axios';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';

export const SignUpPage = () => {
  const [form, setForm] = useState({
    name: '',
    email: '',
    domain_address: '',
    address: '',
    phone_no: ''
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    const res = await axios.post('http://localhost:8080/signUp', form);
    alert('Organization created!');
  } catch (err) {
    alert('Error creating Organization');
  }
};

  return (
    <Container fluid className="min-vh-100 d-flex align-items-center justify-content-center">
      <Row className="w-100 justify-content-center">
        <Col xs={12} sm={10} md={8} lg={6} xl={5}>
          <h1 className="text-center mb-4 text-dark fs-6">Sign Up for Your Organization</h1>
          <Form className="p-4 shadow rounded bg-white" onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
              <Form.Label className='text-dark'>College Name</Form.Label>
              <Form.Control name="name" type="text" value={form.name} onChange={handleChange} required />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className='text-dark'>College Email Address</Form.Label>
              <Form.Control name="email" type="email" value={form.email} onChange={handleChange} required />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className='text-dark'>College Domain Address</Form.Label>
              <Form.Control name="domain_address" type="text" value={form.domain_address} onChange={handleChange} required />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className='text-dark'>College Address</Form.Label>
              <Form.Control name="address" as="textarea" value={form.address} onChange={handleChange} required />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className='text-dark'>College Contact Number</Form.Label>
              <Form.Control name="phone_no" type="text" value={form.phone_no} onChange={handleChange} required />
            </Form.Group>
            <Button variant="primary" type="submit" className="w-100">
              Submit
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};