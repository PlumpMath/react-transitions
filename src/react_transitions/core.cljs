(ns react-transitions.core
  (:require [cljsjs.react]
            [cljsjs.react.dom]))

(enable-console-print!)

(declare render-app)

(defonce app-state (add-watch (atom {})
                              :render
                              (fn [k a o n]
                                (render-app a))))

(defmulti page (fn [state] (get-in @state [:ui :page-shown])))

(defmethod page :page-1 [state]
  (.createElement js/React "div"
                  #js {:className "page page1" :key "page1"} "PAGE-1"))

(defmethod page :page-2 [state]
  (.createElement js/React "div"
                  #js {:className "page page2" :key "page2"} "PAGE-2"))

(defn view [state transition-name transistion-timeout]
  (.createElement
   js/React js/React.addons.CSSTransitionGroup
   #js {:transitionName transition-name
        :transistionTimeout transistion-timeout
        :transitionEnterTimeout transistion-timeout
        :transitionLeaveTimeout transistion-timeout
        :transitionAppear true
        :transitionAppearTimeout transistion-timeout}
   (page state)))

(defn render-app [state]
  (.render js/ReactDOM
           (view state "example" 1000)
           (.getElementById js/document "app")))

(defn on-js-reload []
  (swap! app-state update-in [:__figwheel_counter] inc))

(swap! app-state assoc-in [:ui :page-shown] :page-1)
