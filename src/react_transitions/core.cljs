(ns react-transitions.core
  (:require [cljsjs.react]
            [cljsjs.react.dom]))

(enable-console-print!)

(defn react-transition [transition-name transistion-timeout children]
  (.createElement js/React js/ReactCSSTransitionGroup
                  #js {:transitionName transition-name
                       :transistionTimeout transistion-timeout
                       :transitionAppear true
                       :transitionAppearTimeout transistion-timeout}
                  children))

(defn View [r-page]
  (.createClass
   js/React #js
   {:render (fn []
              (.createElement
               js/React "div"
               (react-transition "example" 1000 #js r-page)))}))

(defn render-app [state]
  (.render js/ReactDOM
           (.createElement js/React View {:id "view"
                                          :key "view"})
           (.getElementById js/document "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
