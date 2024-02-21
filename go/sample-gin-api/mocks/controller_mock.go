// Code generated by MockGen. DO NOT EDIT.
// Source: controller/controller.go
//
// Generated by this command:
//
//	mockgen --source=controller/controller.go --destination=mocks/controller_mock.go --package=mock
//

// Package mock is a generated GoMock package.
package mock

import (
	reflect "reflect"

	gin "github.com/gin-gonic/gin"
	gomock "go.uber.org/mock/gomock"
)

// MockController is a mock of Controller interface.
type MockController struct {
	ctrl     *gomock.Controller
	recorder *MockControllerMockRecorder
}

// MockControllerMockRecorder is the mock recorder for MockController.
type MockControllerMockRecorder struct {
	mock *MockController
}

// NewMockController creates a new mock instance.
func NewMockController(ctrl *gomock.Controller) *MockController {
	mock := &MockController{ctrl: ctrl}
	mock.recorder = &MockControllerMockRecorder{mock}
	return mock
}

// EXPECT returns an object that allows the caller to indicate expected use.
func (m *MockController) EXPECT() *MockControllerMockRecorder {
	return m.recorder
}

// CityInfoHandler mocks base method.
func (m *MockController) CityInfoHandler(ctx *gin.Context) {
	m.ctrl.T.Helper()
	m.ctrl.Call(m, "CityInfoHandler", ctx)
}

// CityInfoHandler indicates an expected call of CityInfoHandler.
func (mr *MockControllerMockRecorder) CityInfoHandler(ctx any) *gomock.Call {
	mr.mock.ctrl.T.Helper()
	return mr.mock.ctrl.RecordCallWithMethodType(mr.mock, "CityInfoHandler", reflect.TypeOf((*MockController)(nil).CityInfoHandler), ctx)
}